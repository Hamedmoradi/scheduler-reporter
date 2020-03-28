package com.pooyabyte.training.service;

import com.pooyabyte.training.domain.Log;

import java.util.Optional;

public interface LogService {
Optional<Log> save(Log log);
}
