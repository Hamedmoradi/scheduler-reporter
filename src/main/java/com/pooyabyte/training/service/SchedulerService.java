package com.pooyabyte.training.service;



import com.pooyabyte.training.domain.Scheduler;
import com.pooyabyte.training.dto.SchedulerDto;

import java.util.List;
import java.util.Optional;

public interface SchedulerService {
    Optional<SchedulerDto> getById(Integer id);

    List<Optional<SchedulerDto>> getAll();

    Optional<SchedulerDto> save(Scheduler scheduler);

    void delete(Scheduler scheduler);
}
