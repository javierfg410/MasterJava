package com.java.master.controller;

import com.java.master.dto.Mensaje;
import com.java.master.dto.PetDto;
import com.java.master.models.Pet;
import com.java.master.security.service.UserService;
import com.java.master.service.PetService;
import com.java.master.service.PetTypeService;

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
@RequestMapping("/pets")
@CrossOrigin(origins = "http://localhost:4200")
public class PetController {
    @Autowired
    PetService petService;
    @Autowired
    PetTypeService petTypeService;
    @Autowired
    UserService userService;

    @GetMapping("/paginate")
    public ResponseEntity<List<Pet>> list() {
        List<Pet> list = petService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        if (!petService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Pet pet = petService.getOne(id).get();
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<?> getByUserId(@PathVariable("id") int id) {
        if (!userService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        List<Pet> list = petService.getByUserId(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/type/{id}")
    public ResponseEntity<?> findByPetType(@PathVariable("id") Long id) {
        if (!petTypeService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        List<Pet> list = petService.findByPetType(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/store")
    public ResponseEntity<?> create(@RequestBody PetDto petDto) {
        if (StringUtils.isBlank(petDto.getName()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        Pet pet = new Pet(petDto.getName(), petDto.getPetType(), petDto.getCost());
        petService.save(pet);
        return new ResponseEntity<>(new Mensaje("el usuario creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody PetDto petDto) {
        if (!petService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (StringUtils.isBlank(petDto.getName()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        Pet pet = petService.getOne(id).get();
        pet.setName(petDto.getName());
        pet.setPetType(petDto.getPetType());
        pet.setCost(petDto.getCost());
        petService.save(pet);
        return new ResponseEntity<>(new Mensaje("el usuario actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!petService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
            petService.delete(id);
        return new ResponseEntity<>(new Mensaje("el usuario eliminado"), HttpStatus.OK);
    }
}
