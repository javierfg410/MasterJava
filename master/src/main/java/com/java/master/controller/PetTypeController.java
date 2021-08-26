package com.java.master.controller;

import com.java.master.dto.Mensaje;
import com.java.master.dto.PetTypeDto;
import com.java.master.models.PetType;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pettypes")
@CrossOrigin(origins = "http://localhost:4200")
public class PetTypeController {
    @Autowired
    PetTypeService petTypeService;

    @GetMapping("/paginate")
    public ResponseEntity<List<PetType>> list() {
        List<PetType> list = petTypeService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        if (!petTypeService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        PetType petType = petTypeService.getOne(id).get();
        return new ResponseEntity<>(petType, HttpStatus.OK);
    }

    @PostMapping("/store")
    public ResponseEntity<?> create(@RequestBody PetTypeDto petTypeDto) {
        if (StringUtils.isBlank(petTypeDto.getName()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        PetType petType = new PetType(petTypeDto.getName());
        petTypeService.save(petType);
        return new ResponseEntity<>(new Mensaje("el usuario creado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!petTypeService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        petTypeService.delete(id);
        return new ResponseEntity<>(new Mensaje("el usuario eliminado"), HttpStatus.OK);
    }
}
