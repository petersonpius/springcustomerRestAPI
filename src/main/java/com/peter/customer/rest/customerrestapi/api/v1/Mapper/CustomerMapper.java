package com.peter.customer.rest.customerrestapi.api.v1.Mapper;

import com.peter.customer.rest.customerrestapi.Domains.Customer;
import com.peter.customer.rest.customerrestapi.api.v1.model.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE= Mappers.getMapper(CustomerMapper.class);

    @Mapping(source="firstName",target = "first_Name")
    @Mapping(source = "lastName",target="last_Name")
    CustomerDTO customerToCustomerDTO(Customer customer);

    @Mapping(source="first_Name",target = "firstName")
    @Mapping(source = "last_Name",target="lastName")
    Customer customerDTOToCustomer(CustomerDTO customerDTO);

}
