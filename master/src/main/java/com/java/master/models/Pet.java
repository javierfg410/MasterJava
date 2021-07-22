package com.java.master.models;

import javax.persistence.*;

@Entity
@Table (name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;


    @Column( nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="type_id")
    private PetType petType;

    @Column( nullable = false)
    private Double cost;


    public Pet() {
    }
    
    public Pet( String name, PetType petType, Double cost) {
        this.name = name;
        this.petType = petType;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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