package com.pooyabyte.training.event;

import com.pooyabyte.training.domain.Customer;
import com.pooyabyte.training.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;


public class CustomerEvent extends ApplicationEvent {
private static final long serialVersionUID = 1L;

private String message;
@Autowired
private Customer customer;

public CustomerEvent(Object source, String message, Customer customer) {
	super(source);
	this.message = message;
	this.customer = customer;
}

public String getMessage() {
	return message;
}

public Customer getCustomer() {
	return customer;
}

public String toString() {
	return "My Custom Event";
}

}
