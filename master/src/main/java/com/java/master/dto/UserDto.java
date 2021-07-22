package com.java.master.dto;

import javax.validation.constraints.*;

import com.java.master.models.Role;

public class UserDto {
    
    private Role role;
    @NotBlank
    private String name;
    private String lastname;
    @Min(6)
    private String username;
    private String password;
    private String phone;
    public UserDto() {
    }
    public UserDto(Role role,String name, String lastname, String username, String password, String phone) {
        this.role = role;
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }


    
    
}
