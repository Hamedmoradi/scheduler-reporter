package com.pooyabyte.training.service;

import com.pooyabyte.training.domain.Customer;
import com.pooyabyte.training.dto.CustomerDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<CustomerDto> getById(String nationalCode);

    List<Optional<CustomerDto>> getAll();

    Optional<CustomerDto> save(Customer customer);

    void delete(Customer customer);

    Optional<Customer> getCustomerByNationalCode(Customer customer);

}
