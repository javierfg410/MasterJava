package com.java.master.security.repository;

import java.util.Optional;

import com.java.master.security.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
   
    Optional <User> findByUsername (String username);
    Optional <User> findByPhone (int phone);
    boolean existsByUsername (String username);
    boolean existsByPhone (int phone);

}
