package com.pooyabyte.training.service;



import com.pooyabyte.training.domain.EmailNotification;
import com.pooyabyte.training.dto.EmailNotificationDto;

import java.util.List;
import java.util.Optional;

public interface EmailService {
    Optional<EmailNotificationDto> getById(Integer id);

    List<Optional<EmailNotificationDto>> getAll();

    Optional<EmailNotificationDto> save(EmailNotification emailsContext);

    void delete(EmailNotification emailsContext);
}
