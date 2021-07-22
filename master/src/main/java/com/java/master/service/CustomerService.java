package com.java.master.service;

import com.java.master.models.Customer;

import javax.transaction.Transactional;

import com.java.master.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {
    
    @Autowired
    CustomerRepository customerRepository;
    
    public List <Customer> list(){
        return customerRepository.findAll();
    }
    public Optional <Customer> getOne(Long id){
        return customerRepository.findById(id);
    }
    public Optional <Customer> getDni(String dni){
        return customerRepository.findByDni(dni);
    }
    public void save(Customer Customer){
        customerRepository.save(Customer);
    }

    public void delete(Long id){
        customerRepository.deleteById(id);
    }
    public boolean existsById (Long id){
        return customerRepository.existsById(id);
    }
    public boolean existsByDni (String dni){
        return customerRepository.existsByDni(dni);
    }

}
