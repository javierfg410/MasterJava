package com.java.master.dto;

import javax.validation.constraints.*;

public class PetTypeDto {
    
    @NotBlank
    private String name;

    public PetTypeDto() {
    }
    public PetTypeDto(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }  
}
