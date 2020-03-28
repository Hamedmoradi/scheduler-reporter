package com.pooyabyte.training.service.serviceImpl;

import com.pooyabyte.training.domain.Customer;
import com.pooyabyte.training.dto.CustomerDto;
import com.pooyabyte.training.repository.CustomerRepository;
import com.pooyabyte.training.service.CustomerService;
import com.pooyabyte.training.event.publisher.CustomEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService, ApplicationEventPublisherAware {

@Autowired
private CustomerRepository customerRepository;

@Autowired
private ApplicationEventPublisher publisher;

@Autowired
private CustomEventPublisher customEventPublisher;



@EventListener
@Override
public Optional<CustomerDto> getById(String nationalCode) {
	Optional<Customer> optionalCustomer = customerRepository.findById(Long.valueOf(nationalCode));
	if (optionalCustomer.isPresent()) {
		return CustomerDto.customerToCustomerDto(optionalCustomer.get());
	} else {
		return Optional.empty();
	}
}

@Override
public List<Optional<CustomerDto>> getAll() {
	return CustomerDto.customerToCustomerDto(customerRepository.findAll());
}

@Override
@Transactional
public Optional<CustomerDto> save(Customer customer) {
	Customer savedCustomer = customerRepository.save(customer);
	System.out.println("Publishing custom event.");
	publisher.publishEvent(savedCustomer);
	customEventPublisher.publish("hiiiiiiiiiii",savedCustomer);;
	return CustomerDto.customerToCustomerDto(savedCustomer);
}

@Override
public void delete(Customer customer) {
	customerRepository.delete(customer);
}

@Override
public Optional<Customer> getCustomerByNationalCode(Customer customer) {
	Optional<Customer> customerByNationalCode = Optional.ofNullable(customerRepository.findCustomerByNationalCode(customer.getNationalCode()));
	if (customerByNationalCode.isPresent()) {
		return customerByNationalCode;
	} else {
		return Optional.empty();
	}
}

@Override
public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
this.publisher=publisher;
}
}



