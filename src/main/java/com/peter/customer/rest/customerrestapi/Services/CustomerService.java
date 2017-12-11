package com.peter.customer.rest.customerrestapi.Services;

import com.peter.customer.rest.customerrestapi.Domains.Customer;
import com.peter.customer.rest.customerrestapi.api.v1.model.CustomerDTO;
import com.peter.customer.rest.customerrestapi.api.v1.model.CustomerListDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    public List<CustomerDTO> getAllCustomers();

    public CustomerDTO getCustomerByFirstName(String name);

    public CustomerDTO getCustomerById(Long id);

    public CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    public CustomerDTO updateCustomer(Long id,CustomerDTO customerDTO);


}
