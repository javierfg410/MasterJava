package com.java.master.service;

import com.java.master.models.Pet;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.java.master.repository.PetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Transactional
public class PetService {
    
    @Autowired
    PetRepository petRepository;

    public List <Pet> list(){
        return petRepository.findAllDontSold();

    }
    public List <Pet> findByPetType(Long id){
        return petRepository.findByPetType(id);
    }
    public List <Pet> getByUserId(int id){
        return petRepository.getByUserId(id);
    }
    public Optional <Pet> getOne(Long id){
        return petRepository.findById(id);
    }

    public void save(Pet Pet){
        petRepository.save(Pet);
    }

    public void delete(Long id){
        petRepository.deleteById(id);
    }
    public boolean existsById (Long id){
        return petRepository.existsById(id);
    }
    
}
