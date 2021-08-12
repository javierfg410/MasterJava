package com.java.master.security.repository;

import java.util.Optional;

import com.java.master.security.entity.Role;
import com.java.master.security.enums.RoleName;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <Role, Integer>{
    Optional <Role> findByRoleName (RoleName roleName);
}
