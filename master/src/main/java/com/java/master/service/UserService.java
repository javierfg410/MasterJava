package com.java.master.service;

import com.java.master.models.User;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.java.master.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Transactional
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public List <User> list(){
        return userRepository.findAll();
    }

    public Optional <User> getOne(Long id){
        return userRepository.findById(id);
    }

    public Optional <User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public Optional <User> findByPhone(String phone){
        return userRepository.findByPhone(phone);
    }
    public void save(User user){
        userRepository.save(user);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
    public boolean existsById (Long id){
        return userRepository.existsById(id);
    }
    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
    public boolean existsByPhone(String phone){
        return userRepository.existsByPhone(phone);
    }
    
}
