/*
COMENTAR UNA VEZ USADO LA PRIMERA VEZ

package com.java.master.util;

import java.util.Set;
import java.util.HashSet;

import com.java.master.security.entity.Role;
import com.java.master.security.entity.User;
import com.java.master.security.enums.RoleName;
import com.java.master.security.service.RoleService;
import com.java.master.security.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;




@Component
public class CreateRoles implements CommandLineRunner{

    @Autowired
    RoleService roleService;
    
    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role(RoleName.ROLE_ADMIN);
        Role roleUser = new Role(RoleName.ROLE_USER);
        roleService.save(roleAdmin);
        roleService.save(roleUser);
    
        User admin = userService.getByUsername("admin").get();
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
        admin.setRoles(roles);
        userService.save(admin);
    
}
}
*/