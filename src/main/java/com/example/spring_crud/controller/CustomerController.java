package com.example.spring_crud.controller;

import com.example.spring_crud.DTO.CustomerDTO;
import com.example.spring_crud.entity.Customer;
import com.example.spring_crud.exception.CustomerNotFoundException;
import com.example.spring_crud.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    Map<String, Object> response = new HashMap<>();
    @PostMapping("/addRecord")
    public ResponseEntity createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Map<String, Object> response = customerService.createCustomer(customerDTO);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllCustomers()
    {
        List<Customer> customer = customerService.getAllCustomers();
        if(customer.isEmpty())
        {
            response.put("success", false);
            response.put("message", "No record found");
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customer, HttpStatus.OK);
    }

    @DeleteMapping("/removeRecord/{customerNumber}")
    public ResponseEntity<Map<String, Object>> deleteCustomer(@PathVariable String customerNumber)
    {
        ResponseEntity<Map<String, Object>> customer=customerService.deleteCustomer(customerNumber);
        return new ResponseEntity(customer,customer.getStatusCode());
    }

    @PutMapping("/updateRecord/{customerNumber}")
    public ResponseEntity<Object> updateCustomer(@PathVariable String customerNumber,@Valid @RequestBody CustomerDTO customerDTO)
    {
        try {
            Map<String, Object> updatedCustomer = customerService.updateCustomer(customerNumber, customerDTO);
            return ResponseEntity.ok(updatedCustomer);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update customer. Error: " + e.getMessage());
        }
    }
}
