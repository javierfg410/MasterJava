/*
COMENTAR UNA VEZ USADO LA PRIMERA VEZ
*/
package com.java.master.util;

import com.java.master.security.entity.User;
import com.java.master.security.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class CreateAdmin implements CommandLineRunner{

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User admin = new User("admin", "admin", "admin", passwordEncoder.encode("12345678"), 666666666);

        userService.save(admin);
    
    
}
}
