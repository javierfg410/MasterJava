package com.java.master.dto;

import java.sql.Date;

import com.java.master.models.Customer;
import com.java.master.models.Pet;
import com.java.master.security.entity.User;

public class SaleDto {

    private User user;
    private Customer customer;
    private Pet pet;
    private Date day;
    public SaleDto() {
    }
    public SaleDto(User user, Customer customer, Pet pet, Date day) {
        this.user = user;
        this.customer = customer;
        this.pet = pet;
        this.day = day;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Pet getPet() {
        return pet;
    }
    public void setPet(Pet pet) {
        this.pet = pet;
    }
    public Date getDay() {
        return day;
    }
    public void setDay(Date day) {
        this.day = day;
    }

    
}
