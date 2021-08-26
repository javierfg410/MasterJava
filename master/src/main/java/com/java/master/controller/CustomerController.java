package com.java.master.controller;

import java.util.List;

import com.java.master.dto.CustomerDto;
import com.java.master.dto.Mensaje;
import com.java.master.models.Customer;
import com.java.master.service.CustomerService;

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
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/paginate")
    public ResponseEntity<List<Customer>> list() {
        List<Customer> list = customerService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        if (!customerService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
            Customer customer = customerService.getOne(id).get();
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> findByPetType(@PathVariable("dni") String dni) {
        if (!customerService.existsByDni(dni))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
            Customer customer = customerService.getDni(dni).get();
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    @PostMapping("/store")
    public ResponseEntity<?> create(@RequestBody CustomerDto customerDto) {
        if (StringUtils.isBlank(customerDto.getName()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        Customer customer = new Customer(customerDto.getDni(), customerDto.getName(), 
        customerDto.getLastname(),customerDto.getAddress(), 
        customerDto.getCountry(), customerDto.getEmail(),customerDto.getPhone());
        
        customerService.save(customer);
        return new ResponseEntity<>(new Mensaje("el usuario creado"), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody CustomerDto customerDto) {
        if (!customerService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (StringUtils.isBlank(customerDto.getName()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        Customer customer = customerService.getOne(id).get();
        customer.setDni(customerDto.getDni());
        customer.setName(customerDto.getName());
        customer.setLastname(customerDto.getLastname());
        customer.setAddress(customerDto.getAddress());
        customer.setCountry(customerDto.getCountry());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        customerService.save(customer);
        return new ResponseEntity<>(new Mensaje("el usuario actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!customerService.existsById(id))
            return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
            customerService.delete(id);
        return new ResponseEntity<>(new Mensaje("el usuario eliminado"), HttpStatus.OK);
    }
}
