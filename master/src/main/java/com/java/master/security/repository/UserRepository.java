package com.java.master.security.repository;

import java.util.List;
import java.util.Optional;

import com.java.master.security.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
   
    @Query(
        value = "SELECT * FROM master.user where username != 'admin'",
        nativeQuery = true)
    List <User>findAllDontAdmin();

    Optional <User> findByUsername (String username);
    Optional <User> findByPhone (int phone);
    boolean existsByUsername (String username);
    boolean existsByPhone (int phone);

}
