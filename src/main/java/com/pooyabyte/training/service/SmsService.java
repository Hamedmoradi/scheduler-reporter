package com.pooyabyte.training.service;


import com.pooyabyte.training.domain.SmsNotification;
import com.pooyabyte.training.dto.SmsNotificationDto;

import java.util.List;
import java.util.Optional;


public interface SmsService {
    Optional<SmsNotificationDto> getById(Integer id);

    List<Optional<SmsNotificationDto>> getAll();

    Optional<SmsNotificationDto> save(SmsNotification smsNotification);

    void delete(SmsNotification smsNotification);
}
