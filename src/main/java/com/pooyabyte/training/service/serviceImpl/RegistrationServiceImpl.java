package com.pooyabyte.training.service.serviceImpl;

import com.pooyabyte.training.service.RegistrationService;
import com.pooyabyte.training.domain.*;
import com.pooyabyte.training.dto.ClientDto;
import com.pooyabyte.training.dto.CustomerDto;
import com.pooyabyte.training.enums.AvailableService;
import com.pooyabyte.training.enums.MessageRegistration;
import com.pooyabyte.training.enums.Status;
import com.pooyabyte.training.exception.CustomerRegisteredPreviouslyException;
import com.pooyabyte.training.exception.NotificationTypeNotSelectedException;
import com.pooyabyte.training.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationService.class);

    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private NotificationMessageTypeService notificationMessageTypeService;
    @Autowired
    private SchedulerService schedulerService;
    
    public ClientDto selectNotification(ClientDto clientDto) {
        if (checkSelectedItem(clientDto)) {
            throw new NotificationTypeNotSelectedException();
            //TODO log in database
        } else {
            Customer customer = validateNewCustomer(clientDto);
            createNotificationMessage(clientDto, customer);
        }
        return clientDto;
    }


    public Customer validateNewCustomer(ClientDto clientDto) {
        Customer customer = new Customer();
        try {
            LOGGER.debug("--------------------------customer checked that duplicate national Code does not register again--------------------------");
            List<Optional<CustomerDto>> allCustomers = customerService.getAll();
            if (!isDuplicateCustomer(clientDto, allCustomers)) {
                createCustomer(clientDto, customer);
            } else {
                throw new CustomerRegisteredPreviouslyException();
                //TODO log in database
            }
        } catch (InvalidDataAccessApiUsageException e) {
            LOGGER.debug("--------------------------InvalidDataAccessApiUsageException-------------------------------------------------------------");
                //TODO log in database
        }
        return customer;
    }

    private boolean isDuplicateCustomer(ClientDto clientDto, List<Optional<CustomerDto>> allCustomers) {
        for (Optional<CustomerDto> customerDto : allCustomers) {
            if ((customerDto.isPresent() && customerDto.get().getNationalCode().equals(clientDto.getNationalCode()))) {
                return true;
            }
        }
        return false;
    }

    private void createCustomer(ClientDto clientDto, Customer customer) {
        customer.setName(clientDto.getName());
        customer.setFamily(clientDto.getFamily());
        customer.setNationalCode(clientDto.getNationalCode());
        customer.setEmailAddress(clientDto.getEmailAddress());
        customer.setCellphone(clientDto.getCellphone());
        customerService.save(customer);
    }

    public boolean checkSelectedItem(ClientDto clientDto) {
        return !clientDto.isEmailChecked() && !clientDto.isSmsChecked();
    }


    private void createNotificationMessage(ClientDto clientDto, Customer customer) {
        String[] title = clientDto.getMessageTitleService();
        ArrayList<Notification> notificationList = new ArrayList<>();
        Notification emailMessagesType = null;
        Notification smsMessagesType = null;


        for (String titles : title) {

            if (clientDto.isEmailChecked()) {

                emailMessagesType = beforeCreateNotificationMessage(clientDto, MessageRegistration.EMAIL.getServiceCode(), titles);
            }
            if (clientDto.isSmsChecked()) {

                smsMessagesType = beforeCreateNotificationMessage(clientDto, MessageRegistration.SMS.getServiceCode(), titles);
            }
        }
        notificationList.add(emailMessagesType);
        notificationList.add(smsMessagesType);
        createNotificationMessageType(clientDto, customer, notificationList);


    }

    private void createNotificationMessageType(ClientDto clientDto, Customer customer, ArrayList<Notification> notifications) {
        for (Notification notification : notifications) {

            if (notification instanceof EmailNotification) {

                processSavingNotificationMessageType(notification, MessageRegistration.EMAIL.getServiceCode(), clientDto, customer);

            } else if (notification instanceof SmsNotification) {

                processSavingNotificationMessageType(notification, MessageRegistration.SMS.getServiceCode(), clientDto, customer);
            }
        }
    }

    private void processSavingNotificationMessageType(Notification messagesTypes, int code, ClientDto clientDto, Customer customer) {
        NotificationMessageType notificationMessageType =
                saveMessageType(clientDto, customer, MessageRegistration.setNotificationService(code));
        setNotificationMessageTypeId(messagesTypes, notificationMessageType);
    }

    private Notification beforeCreateNotificationMessage(ClientDto clientDto, int serviceCode, String title) {
        return prepareService(title, getNotificationFactory(clientDto, serviceCode));
    }

    private Notification getNotificationFactory(ClientDto clientDto, int serviceCode) {

        Notification notification = null;

        if (serviceCode == 1000) {
            notification = new EmailNotification(clientDto.getEmailAddress(), Status.SUCCESS.getResultCode());
        } else if (serviceCode == 2000) {
            notification = new SmsNotification(clientDto.getCellphone(), Status.SUCCESS.getResultCode());
        }
        return notification;
    }


    private Notification prepareService(String title, Notification notification) {
        Notification messagesTypes = initializeMessagesTypes(notification, title);
        afterSetMessages(messagesTypes);
        return messagesTypes;
    }

    private Notification initializeMessagesTypes(Notification notification, String title) {
        switch (title) {
            case "accounthistory":
                createContext(notification, title, AvailableService.ACCOUNT_HISTORY);
                break;
            case "paymentfacility":
                createContext(notification, title, AvailableService.PAYMENT_FACILITY);
                break;
            case "accountstatement":
                createContext(notification, title, AvailableService.ACCOUNT_STATEMENT);
                break;

        }

        notification.setCreatedOn(new Date());
        return notification;
    }

    private void afterSetMessages(Notification notificationChild) {

        if (notificationChild instanceof EmailNotification) {
            emailService.save((EmailNotification) notificationChild);
            saveSchedulerService(notificationChild, MessageRegistration.EMAIL);
        } else if (notificationChild instanceof SmsNotification) {
            smsService.save((SmsNotification) notificationChild);
            saveSchedulerService(notificationChild, MessageRegistration.SMS);

        }

    }

    private NotificationMessageType saveMessageType(ClientDto clientDto, Customer customer, MessageRegistration messageRegistration) {
        NotificationMessageType messageType = new NotificationMessageType();
        messageType.setMessageTitle(clientDto.getMessageTitleService());
        messageType.setCustomerId(customer);
        messageType.setActive(true);
        messageType.setType(messageRegistration.getServiceName());
        notificationMessageTypeService.save(messageType);
        return messageType;
    }

    private void setNotificationMessageTypeId(Notification messagesTypes, NotificationMessageType notificationMessageType) {
        messagesTypes.setNotificationMessageTypeId(notificationMessageType);
    }

    private void saveSchedulerService(Notification notification, MessageRegistration serviceType) {
        Scheduler scheduler = new Scheduler();
        scheduler.setStatus(Status.SUCCESS.getResultCode());//todo this is wrong ,it is must be set after running provider
        scheduler.setServiceCode(serviceType.getServiceCode());
        scheduler.setServiceName(serviceType.getServiceName());
        scheduler.setBusy(true);
        scheduler.setRequestDate(new Date());
        scheduler.setNotificationId(notification);
        this.schedulerService.save(scheduler);
    }


    private void createContext(Notification notification, String titles, AvailableService availableService) {
        notification.setContext(availableService.contextValue(titles));
        notification.setMessageType(titles);
    }

}
