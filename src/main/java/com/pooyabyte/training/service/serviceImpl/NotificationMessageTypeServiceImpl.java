package com.pooyabyte.training.service.serviceImpl;

import com.pooyabyte.training.domain.NotificationMessageType;
import com.pooyabyte.training.dto.NotificationMessageTypeDto;
import com.pooyabyte.training.exception.CommonNotFoundException;
import com.pooyabyte.training.repository.MessageTypeRepository;
import com.pooyabyte.training.service.NotificationMessageTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationMessageTypeServiceImpl implements NotificationMessageTypeService {

@Autowired
private MessageTypeRepository messageTypeRepository;

@Override
public Optional<NotificationMessageTypeDto> getById(Integer id) {
	return messageTypeRepository
			       .findById(id)
			       .map(NotificationMessageTypeDto::notificationMessageTypeToDto)
			       .orElseThrow(CommonNotFoundException::new);
}

@Override
public List<Optional<NotificationMessageTypeDto>> getAll() {
	return NotificationMessageTypeDto.notificationMessageTypeToDto(messageTypeRepository.findAll());
	
}

@Override
public Optional<NotificationMessageTypeDto> save(NotificationMessageType notificationMessageType) {
	NotificationMessageType savedNotificationMessageType = messageTypeRepository.save(notificationMessageType);
	return NotificationMessageTypeDto.notificationMessageTypeToDto(savedNotificationMessageType);
}

@Override
public void delete(NotificationMessageType notificationMessageType) {
	messageTypeRepository.delete(notificationMessageType);
}

@Override
public String getEmailByCustomerId(Optional<NotificationMessageTypeDto> notificationMessageTypeDto) {
	return messageTypeRepository.findByCustomerId_EmailAddress(notificationMessageTypeDto.get().getCustomerId().getNationalCode(),
			notificationMessageTypeDto.get().getType(), notificationMessageTypeDto.get().isActive());
}

@Override
public String getSmsByCustomerId(Optional<NotificationMessageTypeDto> notificationMessageTypeDto) {
	return messageTypeRepository.findByCustomerId_Cellphone(notificationMessageTypeDto.get().getCustomerId().getNationalCode(),
			notificationMessageTypeDto.get().getType(), notificationMessageTypeDto.get().isActive());
}

}
