package com.java.master.repository;

import java.util.Optional;


import com.java.master.models.PetType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PetTypeRepository extends JpaRepository<PetType, Long>{
    Optional <PetType> findByName (String name);
    boolean existsByName (String name);
}
