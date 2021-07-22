package com.java.master.dto;

import javax.validation.constraints.*;

public class CustomerDto {
    
    @NotBlank
    private String dni;

    private String name;
    private String lastname;
    private String address;
    private String country;
    private String email;
    private String phone;
    public CustomerDto() {
    }
    public CustomerDto(String dni, String name, String lastname, String address, String country, String email,
            String phone) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.country = country;
        this.email = email;
        this.phone = phone;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
     
}
