package com.pooyabyte.training.api;

import com.pooyabyte.training.scheduler.ScheduledTasks;
import com.pooyabyte.training.service.RegistrationService;
import com.pooyabyte.training.domain.EmailNotification;
import com.pooyabyte.training.domain.Scheduler;
import com.pooyabyte.training.domain.SmsNotification;
import com.pooyabyte.training.dto.ClientDto;
import com.pooyabyte.training.dto.EmailNotificationDto;
import com.pooyabyte.training.dto.SchedulerDto;
import com.pooyabyte.training.dto.SmsNotificationDto;
import com.pooyabyte.training.service.EmailService;
import com.pooyabyte.training.service.SchedulerService;
import com.pooyabyte.training.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
public class HomeResource {

    @Autowired
    private SchedulerService schedulerService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private ScheduledTasks scheduledTasks;

    @PostMapping(value = "/api/selectNotifier")
    public ClientDto register(@Valid @RequestBody ClientDto clientDto) {

        return registrationService.selectNotification(clientDto);

    }


    @PostMapping("/setSch")
    public Optional<SchedulerDto> initSchedulers(@RequestBody Scheduler scheduler) {
        return schedulerService.save(scheduler);
    }


    @PostMapping("/emailMes")
    public Optional<EmailNotificationDto> emailMessageType(@RequestBody EmailNotification emailNotification) {
        return emailService.save(emailNotification);
    }


    @PostMapping("/smsMes")
    public Optional<SmsNotificationDto> smsMessageType(@RequestBody SmsNotification smsNotification) {
        return smsService.save(smsNotification);

    }

@PostMapping(value = "/loginProcessingUrl")
public String newCustomer() {
    
    return ("redirect:/index.html");
    
}

}
