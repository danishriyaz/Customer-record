package com.example.spring_crud.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class CustomerDTO {


    @NotBlank(message = "name should not be blank")
    @NotNull
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name should contain only letters")
    String name;


    @Email(message = "Email should in proper format")
    String email;
    @Size(min = 10, message = "Phone number must be 10 digits")
    @Pattern(regexp = "\\d{10}", message = "must contain numbers")
    String phoneNo;
    public String getName() {
        return name;
    }

    @Size(min = 3, max = 8, message = "Customer number must be between 3 and 8 digits")
    private String customerNumber;

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

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}
