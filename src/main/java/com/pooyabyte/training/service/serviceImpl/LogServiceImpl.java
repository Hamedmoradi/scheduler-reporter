package com.pooyabyte.training.service.serviceImpl;

import com.pooyabyte.training.domain.Log;
import com.pooyabyte.training.repository.LogRepository;
import com.pooyabyte.training.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LogServiceImpl implements LogService {
@Autowired
private LogRepository logRepository;

@Override
public Optional<Log> save(Log auditLog) {
	Log saveLog = logRepository.save(auditLog);
	return Optional.ofNullable(saveLog);
}
}
