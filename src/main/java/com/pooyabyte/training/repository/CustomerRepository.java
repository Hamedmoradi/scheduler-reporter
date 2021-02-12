package com.pooyabyte.training.repository;

import com.pooyabyte.training.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByNationalCode(String nationalCode);
}
