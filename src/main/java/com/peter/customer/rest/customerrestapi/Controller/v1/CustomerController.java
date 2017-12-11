package com.peter.customer.rest.customerrestapi.Controller.v1;

import com.peter.customer.rest.customerrestapi.Domains.Customer;
import com.peter.customer.rest.customerrestapi.Services.CustomerService;
import com.peter.customer.rest.customerrestapi.api.v1.model.CustomerDTO;
import com.peter.customer.rest.customerrestapi.api.v1.model.CustomerListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers(){
        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(customerService.getAllCustomers()),HttpStatus.OK
        );
    }

    @GetMapping("{fname}")
    public ResponseEntity<CustomerDTO> getByFirstName(@PathVariable String fname){
        CustomerDTO customerDTO=customerService.getCustomerByFirstName(fname);
        return new ResponseEntity<CustomerDTO>(customerDTO,HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(
                customerService.createNewCustomer(customerDTO),HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable String id,@RequestBody CustomerDTO updatedcustomerDTO){
        CustomerDTO customerDTO=customerService.updateCustomer(Long.valueOf(id),updatedcustomerDTO);
        return new ResponseEntity<CustomerDTO>(customerDTO,HttpStatus.OK);
    }
}
