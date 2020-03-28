package com.pooyabyte.training.service.serviceImpl;


import com.pooyabyte.training.domain.Scheduler;
import com.pooyabyte.training.dto.SchedulerDto;
import com.pooyabyte.training.repository.SchedulersRepository;
import com.pooyabyte.training.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchedulersServiceImpl implements SchedulerService {
    @Autowired
    private SchedulersRepository schedulersRepository;

    @Override
    public Optional<SchedulerDto> getById(Integer id) {
        Optional<Scheduler> optionalSchedulers = schedulersRepository.findById(id);
        if (optionalSchedulers.isPresent()) {
            return SchedulerDto.schedulersToSchedulersDto(optionalSchedulers.get());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Optional<SchedulerDto>> getAll() {
        return SchedulerDto.schedulersToSchedulersDto(schedulersRepository.findAll());
    }

    @Override
    public Optional<SchedulerDto> save(Scheduler scheduler) {
        Scheduler savedScheduler = schedulersRepository.save(scheduler);
        return SchedulerDto.schedulersToSchedulersDto(savedScheduler);
    }


    @Override
    public void delete(Scheduler scheduler) {
        schedulersRepository.delete(scheduler);
    }
}
