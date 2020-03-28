package com.pooyabyte.training.event.publisher;

import com.pooyabyte.training.domain.Customer;
import com.pooyabyte.training.event.CustomerEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class CustomEventPublisher  implements ApplicationEventPublisherAware {

@Autowired
private ApplicationEventPublisher publisher;


public void setApplicationEventPublisher (ApplicationEventPublisher publisher) {
	this.publisher = publisher;
}

public void publish(final String message, final Customer customer) {
	System.out.println("Publishing custom event.");
	CustomerEvent ce = new CustomerEvent(this,message,customer);
	publisher.publishEvent(ce);
}
}
