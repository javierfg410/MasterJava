package com.java.master.repository;

import java.util.Optional;

import com.java.master.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional <User> findByUsername (String username);
    Optional <User> findByPhone (String phone);
    boolean existsByUsername (String username);
    boolean existsByPhone (String phone);
}
