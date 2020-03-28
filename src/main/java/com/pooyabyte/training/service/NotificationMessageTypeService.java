package com.pooyabyte.training.service;

import com.pooyabyte.training.domain.NotificationMessageType;
import com.pooyabyte.training.dto.NotificationMessageTypeDto;

import java.util.List;
import java.util.Optional;

public interface NotificationMessageTypeService {

    Optional<NotificationMessageTypeDto> getById(Integer id);

    List<Optional<NotificationMessageTypeDto>> getAll();

    Optional<NotificationMessageTypeDto> save(NotificationMessageType notificationMessageType);

    void delete(NotificationMessageType notificationMessageType);

    String getEmailByCustomerId(Optional<NotificationMessageTypeDto> notificationMessageTypeDto);

    String getSmsByCustomerId(Optional<NotificationMessageTypeDto> notificationMessageTypeDto);
}
