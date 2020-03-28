package com.pooyabyte.training.dto;

import com.pooyabyte.training.domain.Customer;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

@XmlRootElement(name = "customer")
public class CustomerDto implements Serializable {

    private Long id;
    private String nationalCode;
    private String name;
    private String family;
    private String emailAddress;
    private String cellphone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public static Optional<CustomerDto> customerToCustomerDto(Customer customer){
        if(customer !=null ){
            CustomerDto customerDto = new CustomerDto();
            customerDto.setId(customer.getId());
            customerDto.setNationalCode(customer.getNationalCode());
            customerDto.setName(customer.getName());
            customerDto.setFamily(customer.getFamily());
            customerDto.setEmailAddress(customer.getEmailAddress());
            customerDto.setCellphone(customer.getCellphone());
            return Optional.of(customerDto);
        }else{
            return Optional.empty();
        }
    }

    public static List<Optional<CustomerDto>> customerToCustomerDto(Collection<Customer> customers) {
        if (null != customers && customers.size() > 0) {
            List<Optional<CustomerDto>> customerDtoList = new ArrayList<>(customers.size());
            for (Customer   customer : customers) {
                customerDtoList.add(customerToCustomerDto(customer));
            }
            return customerDtoList;
        } else {
            return Collections.EMPTY_LIST;
        }
    }



}

