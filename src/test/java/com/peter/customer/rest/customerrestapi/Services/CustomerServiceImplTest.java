package com.peter.customer.rest.customerrestapi.Services;

import com.peter.customer.rest.customerrestapi.Domains.Customer;
import com.peter.customer.rest.customerrestapi.Repositories.CustomerRepo;
import com.peter.customer.rest.customerrestapi.api.v1.Mapper.CustomerMapper;
import com.peter.customer.rest.customerrestapi.api.v1.model.CustomerDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {


    public static final String FIRST_NAME = "An";

    CustomerService customerService;

    @Mock
    CustomerRepo customerRepo;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService=new CustomerServiceImpl(customerRepo,CustomerMapper.INSTANCE);

    }

    @Test
    public void getCustomerByFirstName() throws Exception {
        Customer cust1=new Customer(3L, FIRST_NAME,"Du");

        when(customerRepo.findByFirstName(anyString())).thenReturn(Optional.of(cust1));

        CustomerDTO customerDTO=customerService.getCustomerByFirstName(FIRST_NAME);

        assertEquals(FIRST_NAME,customerDTO.getFirst_Name());
    }

    @Test
    public void getAllCustomers() throws Exception {
        Customer cust1=new Customer(3L, FIRST_NAME,"Du");
        Customer cust2=new Customer(4L, "Jn","LU");

        List<Customer> customers= Arrays.asList(cust1,cust2);

        when(customerRepo.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOS=customerService.getAllCustomers();

        assertEquals(2,customerDTOS.size());

    }

   // @Test
    public void getCustomerById() throws Exception {
    }

    @Test
    public void createNewCustomer() throws Exception {
        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setFirst_Name(FIRST_NAME);

        Customer saveCustomer=new Customer();
        saveCustomer.setFirstName(customerDTO.getFirst_Name());
        saveCustomer.setId(8L);
        saveCustomer.setLastName(customerDTO.getLast_Name());

        when(customerRepo.save(any(Customer.class))).thenReturn(saveCustomer);
        CustomerDTO savedCustomerDTO= customerService.createNewCustomer(customerDTO);

        assertEquals(FIRST_NAME,savedCustomerDTO.getFirst_Name());
        assertEquals("api/v1/customer/8",savedCustomerDTO.getCustomerUrl());
    }

    @Test
    public void updateCustomer() throws Exception {
        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setFirst_Name(FIRST_NAME);

        Customer updatedCustomer=new Customer();
        updatedCustomer.setFirstName(customerDTO.getFirst_Name());
        updatedCustomer.setId(8L);
        updatedCustomer.setLastName(customerDTO.getLast_Name());

        when(customerRepo.save(any(Customer.class))).thenReturn(updatedCustomer);
        CustomerDTO updatedCustomerDTO= customerService.updateCustomer(8L,customerDTO);

        assertEquals(FIRST_NAME,updatedCustomerDTO.getFirst_Name());
        assertEquals("api/v1/customer/8",updatedCustomerDTO.getCustomerUrl());
    }

}