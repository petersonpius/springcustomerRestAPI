package com.peter.customer.rest.customerrestapi.bootstrap;

import com.peter.customer.rest.customerrestapi.Domains.Customer;
import com.peter.customer.rest.customerrestapi.Repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public void run(String... args) throws Exception {
        Customer customer1=new Customer(1L,"Peterson","Chiramel");
        Customer customer2=new Customer(2L,"Dhanya","Babu");

        customerRepo.save(customer1);
        customerRepo.save(customer2);

        System.out.println("Data Added "+ customerRepo.count());

    }
}
