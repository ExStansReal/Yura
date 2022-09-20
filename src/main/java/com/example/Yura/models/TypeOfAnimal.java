package com.example.Yura.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class TypeOfAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_typeofanimal;


    @NotEmpty(message = "Поле не может быть пустым!")
    @Size(message = "Поле не может быть меньше или больше", min = 1, max = 100)
    private String name, description;


    @OneToMany(mappedBy = "type", fetch = FetchType.EAGER)
    private Collection<Animal> animals;


    public TypeOfAnimal(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public TypeOfAnimal() {

    }

    public Long getId_typeofanimal() {
        return id_typeofanimal;
    }

    public void setId_typeofanimal(Long id_typeofanimal) {
        this.id_typeofanimal = id_typeofanimal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Collection<Animal> animals) {
        this.animals = animals;
    }
}

