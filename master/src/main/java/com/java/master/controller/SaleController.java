package com.java.master.controller;

import java.util.List;

import com.java.master.dto.Mensaje;
import com.java.master.dto.SaleDto;
import com.java.master.models.Sale;
import com.java.master.models.User;
import com.java.master.service.CustomerService;
import com.java.master.service.PetService;
import com.java.master.service.SaleService;
import com.java.master.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
@CrossOrigin(origins = "http://localhost:4200")
public class SaleController {
    @Autowired
    SaleService saleService;
    @Autowired
    PetService petService;
    @Autowired
    CustomerService customerService;
    @Autowired
    UserService userService;

    @GetMapping("/paginate")
    public ResponseEntity<List<Sale>> list() {
        List<Sale> list = saleService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        if (!saleService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Sale sale = saleService.getOne(id).get();
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findByPetType(@PathVariable("id") Long id) {
        if (!userService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        User user = userService.getOne(id).get();
        List<Sale> list = saleService.findByUser(user);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/store")
    public ResponseEntity<?> create(@RequestBody SaleDto saleDto) {
        if (!userService.existsById(saleDto.getUser().getId()))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (!petService.existsById(saleDto.getPet().getId()))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (!customerService.existsById(saleDto.getCustomer().getId()))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);

        Sale sale = new Sale(saleDto.getUser(), saleDto.getCustomer(),saleDto.getPet(), saleDto.getDay());
        saleService.save(sale);
        return new ResponseEntity<>(new Mensaje("el usuario creado"), HttpStatus.OK);
    }

}
