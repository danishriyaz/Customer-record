package com.example.spring_crud.service;

import com.example.spring_crud.DTO.CustomerDTO;
import com.example.spring_crud.entity.Customer;
import com.example.spring_crud.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.spring_crud.utility.mapper.convertToEntity;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    Map<String, Object> response = new HashMap<>();

    public Map<String, Object> createCustomer(CustomerDTO customerDTO) {

        System.out.print("saving to db");
        try {
            Customer customerAlreadyExist = customerRepository.findByEmail(customerDTO.getEmail());
            if (customerAlreadyExist != null) {
                response.put("success", false);
                response.put("message", "Email address already exists");

            } else {
                Customer customer = convertToEntity(customerDTO);
                Customer savedCustomer = customerRepository.save(customer);
                response.put("success", true);
                response.put("message", "Customer saved successfully");
                response.put("id", savedCustomer.getId());
                response.put("customer", savedCustomer);

            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    @Transactional
    public List<Customer> getAllCustomers() {
        System.out.print("fetching from db");
        List<Customer> customers = null;
        try {
            customers = customerRepository.findAll();

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return customers;
    }

    @Transactional
    public Optional<Customer> getCustomerByCustomerNumber(String customerNubmer) {
        System.out.print("fetching from db");
        Optional<Customer> customer = Optional.empty();
        try {
            customer = Optional.ofNullable(customerRepository.findByCustomerNumber(customerNubmer));
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return customer;
    }

    @Transactional
    public ResponseEntity<Map<String, Object>> deleteCustomer(String customerNumber) {

        try {
            Customer customer = customerRepository.findByCustomerNumber(customerNumber);

            if (customer == null) {
                response.put("success", false);
                response.put("message", "No Record found with Customer number: " + customerNumber);
                return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

            } else {
                customerRepository.deleteByCustomerNumber(customerNumber);
                response.put("success", true);
                response.put("message", "Customer with number : " + customerNumber + " deleted successfully");
            }
        } catch (EmptyResultDataAccessException ex) {
            response.put("success", false);
            response.put("message", "Customer with number: " + customerNumber + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (DataAccessException ex) {
            response.put("success", false);
            response.put("message", "Failed to delete customer. Error: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return new ResponseEntity(response,HttpStatus.OK);
    }

   @Transactional
    public  Map<String, Object> updateCustomer(String customerNumber, CustomerDTO customerDTO) {

       Customer existingCustomer = customerRepository.findByCustomerNumber(customerNumber);
       Customer updatedCustomer = null;
       if (existingCustomer != null) {
           Customer customer = convertToEntity(customerDTO);
           customer.setCustomerNo(existingCustomer.getCustomerNumber());
           customer.setName(existingCustomer.getName());
           customer.setPhoneNo(existingCustomer.getPhoneNo());
           updatedCustomer = customerRepository.save(customer);
           response.put("success", true);
           response.put("message", "Customer updated successfully");
           response.put("id", updatedCustomer.getId());
           response.put("customer", updatedCustomer);
       } else {
           response.put("success", false);
           response.put("message", "Customer record not found");
       }
       return response;
   }
}
