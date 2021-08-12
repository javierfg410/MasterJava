package com.java.master.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.java.master.models.Sale;
import com.java.master.security.entity.User;
import com.java.master.repository.SaleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Transactional
public class SaleService {
    @Autowired
    SaleRepository saleRepository;
    
    public List <Sale> list(){
        return saleRepository.findAll();
    }
    public Optional <Sale> getOne(Long id){
        return saleRepository.findById(id);
    }
    public List <Sale> findByUser(User user){
        return saleRepository.findByUser(user);
    }
    public void save(Sale sale){
        saleRepository.save(sale);
    }
    public void delete(Long id){
        saleRepository.deleteById(id);
    }
    public boolean existsById (Long id){
        return saleRepository.existsById(id);
    }
    public boolean existsByUser(User user){
        return saleRepository.existsByUser(user);
    }
}
