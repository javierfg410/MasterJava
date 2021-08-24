package com.java.master.repository;

import java.util.List;
import java.util.Optional;

import com.java.master.models.Pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {


    Optional<Pet> findByName(String name);

    @Query(
        value = "SELECT * FROM master.pets where id not in (SELECT p.id FROM  pets p join sales s on(p.id = s.pet_id)) and type_id = :typeId",
        nativeQuery = true)
    List<Pet> findByPetType(@Param("typeId") Long id);

    boolean existsByName(String name);

    @Query(
        value = "SELECT * FROM master.pets where id not in (SELECT p.id FROM  pets p join sales s on(p.id = s.pet_id)) ",
        nativeQuery = true)
    List<Pet> findAllDontSold();

    @Query(
        value = "SELECT * FROM master.pets where id in (SELECT p.id FROM  pets p join sales s on(p.id = s.pet_id)WHERE s.user_id = :userId)",
        nativeQuery = true)
    List<Pet> getByUserId(@Param("userId") int id);
}
