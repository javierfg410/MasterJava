package com.java.master.dto;

import javax.validation.constraints.*;

import com.java.master.models.PetType;

public class PetDto {
    
    @NotBlank
    private String name;
    
    private PetType petType;
    private Double cost;

    public PetDto() {
    }
    public PetDto(String name, PetType petType, Double cost) {
        this.name = name;
        this.petType = petType;
        this.cost = cost;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public PetType getPetType() {
        return petType;
    }
    public void setPetType(PetType petType) {
        this.petType = petType;
    }
    public Double getCost() {
        return cost;
    }
    public void setCost(Double cost) {
        this.cost = cost;
    }

    
    
}
