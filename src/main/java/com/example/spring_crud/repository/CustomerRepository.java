package com.example.spring_crud.repository;

import com.example.spring_crud.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByEmail(String email);
    Customer findByCustomerNumber(String customerNumber);
    void deleteByCustomerNumber(String customerNumber);


}