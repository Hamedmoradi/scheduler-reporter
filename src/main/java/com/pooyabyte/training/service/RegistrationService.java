package com.pooyabyte.training.service;

import com.pooyabyte.training.domain.Customer;
import com.pooyabyte.training.dto.ClientDto;

public interface RegistrationService {

    ClientDto selectNotification(ClientDto clientDto);

    Customer validateNewCustomer(ClientDto clientDto);

    boolean checkSelectedItem(ClientDto clientDto);
}