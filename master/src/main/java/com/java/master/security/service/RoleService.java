package com.java.master.security.service;

import java.util.Optional;

import com.java.master.security.entity.Role;
import com.java.master.security.enums.RoleName;
import com.java.master.security.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleService {
    
    @Autowired
    RoleRepository roleRepository;

    public Optional <Role> getByRoleName(RoleName roleName){
        return roleRepository.findByRoleName(roleName);
    }
    public void save(Role role){
        roleRepository.save(role);
    }
    
}
