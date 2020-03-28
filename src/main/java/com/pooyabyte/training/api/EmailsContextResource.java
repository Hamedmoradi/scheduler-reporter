package com.pooyabyte.training.api;

import com.pooyabyte.training.domain.EmailNotification;
import com.pooyabyte.training.dto.EmailNotificationDto;
import com.pooyabyte.training.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/emailsContext")
public class EmailsContextResource {

    @Autowired
    private EmailService emailService;

    @GetMapping
    public List<Optional<EmailNotificationDto>> getAll() {
        return emailService.getAll();
    }

    @PostMapping
    public Optional<EmailNotificationDto> save(@RequestBody EmailNotification emailsContext) {
        return emailService.save(emailsContext);
    }
}
