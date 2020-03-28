package com.pooyabyte.training.api;

import com.pooyabyte.training.domain.Scheduler;
import com.pooyabyte.training.dto.SchedulerDto;
import com.pooyabyte.training.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/scheduler")
public class SchedulersResource {

    @Autowired
    private SchedulerService schedulerService;

    @GetMapping
    public List<Optional<SchedulerDto>> getAll() {
        return schedulerService.getAll();
    }

    @PostMapping
    public Optional<SchedulerDto> save(@RequestBody Scheduler scheduler) {
        return schedulerService.save(scheduler);
    }


}
