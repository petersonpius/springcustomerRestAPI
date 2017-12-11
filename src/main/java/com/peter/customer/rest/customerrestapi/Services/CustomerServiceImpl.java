package com.peter.customer.rest.customerrestapi.Services;

import com.peter.customer.rest.customerrestapi.Domains.Customer;
import com.peter.customer.rest.customerrestapi.Repositories.CustomerRepo;
import com.peter.customer.rest.customerrestapi.api.v1.Mapper.CustomerMapper;
import com.peter.customer.rest.customerrestapi.api.v1.model.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepo customerRepo;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepo customerRepo,CustomerMapper customerMapper) {
        this.customerRepo = customerRepo;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepo.
                findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO=customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("api/v1/customer/"+customer.getId());
                    return customerDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerByFirstName(String name) {
        return customerRepo.
                findByFirstName(name)
                .map(customer -> {
                    CustomerDTO customerDTO=customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("api/v1/customer/"+customer.getId());
                    return customerDTO;
                })
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepo.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer customer=customerMapper.customerDTOToCustomer(customerDTO);
        Customer savedCustomer=customerRepo.save(customer);
        customerDTO.setCustomerUrl("api/v1/customer/"+savedCustomer.getId());
        return customerDTO;

    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer=customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);
        Customer updatedCustomer=customerRepo.save(customer);
        customerDTO.setCustomerUrl("api/v1/customer/"+customer.getId());
        return customerDTO;
    }


}
