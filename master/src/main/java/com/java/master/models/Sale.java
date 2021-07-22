package com.java.master.models;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table (name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="pet_id")
    private Pet pet;

    @Column( nullable = false)
    private Date day;


    
    public Sale() {
    }
    
    public Sale(User user, Customer customer, Pet pet, Date day) {
        this.user = user;
        this.customer = customer;
        this.pet = pet;
        this.day = day;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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