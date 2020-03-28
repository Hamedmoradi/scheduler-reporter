package com.pooyabyte.training.api;


import com.pooyabyte.training.domain.SmsNotification;
import com.pooyabyte.training.dto.SmsNotificationDto;
import com.pooyabyte.training.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/smsContext")
public class SmsContextResource {

    @Autowired
    private SmsService smsService;

    @GetMapping
    public List<Optional<SmsNotificationDto>> getAll() {
        return smsService.getAll();
    }

    @PostMapping
    public Optional<SmsNotificationDto> save(@RequestBody SmsNotification smsNotification) {
        return smsService.save(smsNotification);
    }
}
