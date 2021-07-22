package com.java.master.controller;

import com.java.master.dto.Mensaje;
import com.java.master.dto.UserDto;
import com.java.master.models.User;

import com.java.master.service.UserService;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/paginate")
    public ResponseEntity<List<User>> list() {
        List<User> list = userService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        if (!userService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        User user = userService.getOne(id).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/store")
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
        if (StringUtils.isBlank(userDto.getName()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        if (userDto.getUsername().length() < 6)
            return new ResponseEntity<>(new Mensaje("nombre de usuario mayor a 6"), HttpStatus.BAD_REQUEST);
        if (userService.existsByUsername(userDto.getUsername()))
            return new ResponseEntity<>(new Mensaje("username ya utilizado"), HttpStatus.BAD_REQUEST);

        if (userDto.getPhone().length() < 8)
            return new ResponseEntity<>(new Mensaje("nombre de usuario mayor a 6"), HttpStatus.BAD_REQUEST);
        if (userService.existsByPhone(userDto.getPhone()))
            return new ResponseEntity<>(new Mensaje("Telefono ya existe"), HttpStatus.BAD_REQUEST);

        User user = new User(userDto.getRole(), userDto.getName(), userDto.getLastname(), userDto.getUsername(),
                userDto.getPassword(), userDto.getPhone());
        userService.save(user);
        return new ResponseEntity<>(new Mensaje("el usuario creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        if (!userService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (StringUtils.isBlank(userDto.getName()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (userService.findByPhone(userDto.getPhone()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("Telefono ya existe"), HttpStatus.BAD_REQUEST);
        if (userDto.getUsername().length() < 6)
            return new ResponseEntity<>(new Mensaje("nombre de usuario mayor a 6"), HttpStatus.BAD_REQUEST);
        if(userService.existsByUsername(userDto.getUsername())){
            if (userService.findByUsername(userDto.getUsername()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("username ya utilizado"), HttpStatus.BAD_REQUEST);
        }
        

        User user = userService.getOne(id).get();
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        userService.save(user);
        return new ResponseEntity<>(new Mensaje("el usuario actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!userService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        userService.delete(id);
        return new ResponseEntity<>(new Mensaje("el usuario eliminado"), HttpStatus.OK);
    }
}
