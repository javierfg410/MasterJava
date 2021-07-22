package com.java.master.repository;

import java.util.Optional;

import com.java.master.models.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Optional <Customer> findByDni (String dni);
    boolean existsByDni (String dni);
}
