package com.example.spring_crud.utility;

import com.example.spring_crud.DTO.CustomerDTO;
import com.example.spring_crud.entity.Customer;

public class mapper {

    public static Customer convertToEntity(CustomerDTO customerDTO)
    {
        Customer customer=new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhoneNo(customerDTO.getPhoneNo());
        customer.setCustomerNo(customerDTO.getCustomerNumber());
        return customer;

    }
}
