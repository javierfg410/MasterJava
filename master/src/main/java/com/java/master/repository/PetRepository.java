package com.java.master.repository;

import java.util.List;
import java.util.Optional;

import com.java.master.models.Pet;
import com.java.master.models.PetType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface PetRepository extends JpaRepository<Pet, Long>{
    Optional <Pet> findByName (String name);
    List<Pet> findByPetType(PetType petType);
    boolean existsByName (String name);
}
