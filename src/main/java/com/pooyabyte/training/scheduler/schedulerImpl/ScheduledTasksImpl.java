package com.pooyabyte.training.scheduler.schedulerImpl;

import com.pooyabyte.training.domain.Customer;
import com.pooyabyte.training.dto.NotificationMessageTypeDto;
import com.pooyabyte.training.enums.AvailableService;
import com.pooyabyte.training.enums.MessageRegistration;
import com.pooyabyte.training.providers.AbstractProviderService;
import com.pooyabyte.training.providers.EmailProvider;
import com.pooyabyte.training.providers.SmsProvider;
import com.pooyabyte.training.scheduler.ScheduledTasks;
import com.pooyabyte.training.service.CustomerService;
import com.pooyabyte.training.service.EmailService;
import com.pooyabyte.training.service.NotificationMessageTypeService;
import com.pooyabyte.training.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;

@Component
public class ScheduledTasksImpl implements ScheduledTasks {

private static final Logger logger = LoggerFactory.getLogger(ScheduledTasksImpl.class);

@Autowired
private NotificationMessageTypeService notificationMessageTypeService;

@Qualifier("smsProvider")
@Autowired
private AbstractProviderService abstractSmsProviderService;
@Qualifier("emailProvider")
@Autowired
AbstractProviderService abstractEmailProviderService;

@Autowired
private CustomerService customerService;

@Autowired
private EmailService emailService;

@Autowired
private SmsService smsService;

@Scheduled(fixedRate = 60000)
public void sendNotification() {
	logger.debug("----------sending Notification Start-----------");
	for (Optional<NotificationMessageTypeDto> notificationMessageType : notificationMessageTypeService.getAll()) {
		if (notificationMessageType.isPresent()) {
			String type = notificationMessageType.get().getType();
			String[] messageTitles = notificationMessageType.get().getMessageTitle();
			beforeRunningScheduler(type, messageTitles);
			logger.debug("----------show result----------");
		}
	}
}

public void beforeRunningScheduler(String type, String[] titles) {
	for (String title : titles) {
		if (type.equals(MessageRegistration.EMAIL.getServiceName())) {
			
			EmailProvider emailProvider;
			emailProvider = (EmailProvider) abstractEmailProviderService;
			doJob(type, MessageRegistration.EMAIL, "FOR EMAIL SERVICE:", title, emailProvider);
			
			
		} else if (type.equals(MessageRegistration.SMS.getServiceName())) {
			
			SmsProvider smsProvider;
			smsProvider = (SmsProvider) abstractSmsProviderService;
			doJob(type, MessageRegistration.SMS, "FOR SMS SERVICE:", title, smsProvider);
		}
	}
	
}

public void doJob(String submissionType, MessageRegistration messageRegistration,
                  String statement, String bankService, AbstractProviderService provider) {
	if (bankService.equals(AvailableService.PAYMENT_FACILITY.getBankService()) &&
			    submissionType.equals(messageRegistration.getServiceName())) {
		sendToServiceProvider(messageRegistration, AvailableService.PAYMENT_FACILITY, statement, provider);
	} else if (bankService.equals(AvailableService.ACCOUNT_STATEMENT.getBankService()) &&
			           submissionType.equals(messageRegistration.getServiceName())) {
		sendToServiceProvider(messageRegistration, AvailableService.ACCOUNT_STATEMENT, statement, provider);
	} else if (bankService.equals(AvailableService.ACCOUNT_HISTORY.getBankService()) &&
			           submissionType.equals(messageRegistration.getServiceName())) {
		sendToServiceProvider(messageRegistration, AvailableService.ACCOUNT_HISTORY, statement, provider);
	}
}

private void sendToServiceProvider(MessageRegistration messageRegistration, AvailableService availableService, String statement, AbstractProviderService provider) {
	List<Optional<NotificationMessageTypeDto>> all = notificationMessageTypeService.getAll();
	for (Optional<NotificationMessageTypeDto> notificationMessageTypeDto : all) {
		Customer customer = notificationMessageTypeDto.get().getCustomerId();
		Optional<Customer> customerByNationalCode = customerService.getCustomerByNationalCode(customer);
		
		if (customer.getNationalCode().equals(customerByNationalCode.get().getNationalCode()) && messageRegistration.equals(MessageRegistration.EMAIL)) {
			String resultFromDB = notificationMessageTypeService.getEmailByCustomerId(notificationMessageTypeDto);
			runProvider(messageRegistration, availableService, statement, provider, customer, resultFromDB);
		}
		if (customer.getNationalCode().equals(customerByNationalCode.get().getNationalCode()) && messageRegistration.equals(MessageRegistration.SMS)) {
			String resultFromDB = notificationMessageTypeService.getSmsByCustomerId(notificationMessageTypeDto);
			runProvider(messageRegistration, availableService, statement, provider, customer, resultFromDB);
		}
	}
}

private void runProvider(MessageRegistration messageRegistration, AvailableService availableService, String statement, AbstractProviderService provider, Customer customer, String fromDB) {
	provider.sendSimpleMessage(fromDB,
			messageRegistration.getServiceName(),
			availableService.getContext());
	
	logger.info(statement + availableService.getContext() + customer);
	
	//todo after send to provider if there is no Exception & send was successfully SET Status for scheduler SUCCESS else another Statuses ...
}

}
