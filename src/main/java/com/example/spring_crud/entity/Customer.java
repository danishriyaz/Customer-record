package com.example.spring_crud.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    int id;

    private String customerNumber;
    private String name;

    @Column(name="email",unique = true)
    private String email;

    private String phoneNo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNo(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}
