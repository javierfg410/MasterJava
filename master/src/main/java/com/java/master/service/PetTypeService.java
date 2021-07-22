package com.java.master.service;

import com.java.master.models.PetType;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.java.master.repository.PetTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Transactional
public class PetTypeService {
    
    @Autowired
    PetTypeRepository petTypeRepository;

    public List <PetType> list(){
        return petTypeRepository.findAll();
    }

    public Optional <PetType> getOne(Long id){
        return petTypeRepository.findById(id);
    }
    public void save(PetType PetType){
        petTypeRepository.save(PetType);
    }

    public void delete(Long id){
        petTypeRepository.deleteById(id);
    }
    public boolean existsById (Long id){
        return petTypeRepository.existsById(id);
    }
    public boolean existsByName (String name){
        return petTypeRepository.existsByName(name);
    }
    
}
