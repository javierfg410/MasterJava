package com.java.master.security.service;

import java.util.List;
import java.util.Optional;

import com.java.master.security.repository.UserRepository;
import com.java.master.security.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List <User> list(){
        return userRepository.findAllDontAdmin();
    }

    public Optional <User> getOne(int id){
        return userRepository.findById(id);
    }

    public Optional <User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public Optional <User> findByPhone(int phone){
        return userRepository.findByPhone(phone);
    }
    public void save(User user){
        userRepository.save(user);
    }

    public void delete(int id){
        userRepository.deleteById(id);
    }
    public boolean existsById (int id){
        return userRepository.existsById(id);
    }
    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
    public boolean existsByPhone(int phone){
        return userRepository.existsByPhone(phone);
    }
}
