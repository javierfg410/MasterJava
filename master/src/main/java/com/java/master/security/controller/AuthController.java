package com.java.master.security.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

import javax.validation.Valid;

import com.java.master.dto.Mensaje;
import com.java.master.security.dto.JwtDto;
import com.java.master.security.dto.LoginUser;
import com.java.master.security.dto.NewUser;
import com.java.master.security.entity.Role;
import com.java.master.security.entity.User;
import com.java.master.security.enums.RoleName;
import com.java.master.security.jwt.JwtProvider;
import com.java.master.security.service.RoleService;
import com.java.master.security.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtProvider jwtProvider;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<?> store(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Mensaje("Email invalido o error inesperado"), HttpStatus.BAD_REQUEST);
        if (userService.existsByUsername(newUser.getUsername()))
            return new ResponseEntity<>(new Mensaje("Nombre de usario ya existente"), HttpStatus.BAD_REQUEST);
        if (userService.existsByPhone(newUser.getPhone()))
            return new ResponseEntity<>(new Mensaje("Telefono ya existente"), HttpStatus.BAD_REQUEST);
        User user = new User(newUser.getName(), newUser.getLastname(), newUser.getUsername(),
                passwordEncoder.encode(newUser.getPassword()), newUser.getPhone());
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
        if (newUser.getRoles().contains("admin"))
            roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/paginate")
    public ResponseEntity<List<User>> list() {
        List<User> list = userService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        if (!userService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
            User user = userService.getOne(id).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users/username/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable("username") String username) {
        if (!userService.existsByUsername(username))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
            User user = userService.getByUsername(username).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody NewUser newUser) {
        if (!userService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (StringUtils.isBlank(newUser.getName()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if ( userService.existsByUsername(newUser.getUsername()) &&  userService.getOne(id).get().getId() != id)
            return new ResponseEntity<>(new Mensaje( "Nombre de usario ya existente" ), HttpStatus.BAD_REQUEST);
        if (userService.existsByPhone(newUser.getPhone()) &&  userService.getOne(id).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("Telefono ya existente"), HttpStatus.BAD_REQUEST);
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
        User user = userService.getOne(id).get();
        user.setName(newUser.getName());
        user.setLastname(newUser.getLastname());
        user.setUsername(newUser.getUsername());
        user.setPhone(newUser.getPhone());
        userService.save(user);
        return new ResponseEntity<>(new Mensaje("el usuario actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!userService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
            userService.delete(id);
        return new ResponseEntity<>(new Mensaje("el usuario eliminado"), HttpStatus.OK);
    }
}
