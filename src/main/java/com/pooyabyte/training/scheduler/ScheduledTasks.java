package com.pooyabyte.training.scheduler;
import com.pooyabyte.training.enums.MessageRegistration;
import com.pooyabyte.training.providers.AbstractProviderService;
import com.pooyabyte.training.providers.EmailProvider;
import com.pooyabyte.training.providers.SmsProvider;

public interface ScheduledTasks {

    void sendNotification();

    void beforeRunningScheduler(String type, String[] titles);

    void doJob(String submissionType, MessageRegistration messageRegistration, String statement, String bankService,AbstractProviderService abstractProviderService);
}
