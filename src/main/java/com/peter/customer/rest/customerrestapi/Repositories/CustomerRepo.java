package com.peter.customer.rest.customerrestapi.Repositories;

import com.peter.customer.rest.customerrestapi.Domains.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
    public Optional<Customer> findByFirstName(String firstName);
}