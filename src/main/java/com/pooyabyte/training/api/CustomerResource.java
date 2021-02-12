package com.pooyabyte.training.api;

import com.pooyabyte.training.domain.Customer;
import com.pooyabyte.training.dto.CustomerDto;
import com.pooyabyte.training.exception.CustomerRegisteredPreviouslyException;
import com.pooyabyte.training.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")

public class CustomerResource {

@Autowired
private CustomerService customerService;



@GetMapping
public List<Optional<CustomerDto>> getAll() {
	return customerService.getAll();
}

@PostMapping
@ApiOperation(value = "add a customer", response = Iterable.class)
@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully add customer"),
		@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
})
public Optional<CustomerDto> save(@ApiParam(value = "Customer object store in database table", required = true)
                                  @Valid @RequestBody Customer customer) {
		return customerService.save(customer);
}

@RequestMapping
public ResponseEntity<Object> determineDuplicatedCustomer() {
	throw new CustomerRegisteredPreviouslyException();
}
}