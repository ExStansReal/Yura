package com.example.Yura.models;

import javax.persistence.*;

@Entity
public class PrefferedAnimals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_prefferedanimal;

    @ManyToOne(optional = true, cascade = CascadeType.PERSIST)
    private User user;


    @ManyToOne(optional = true, cascade = CascadeType.PERSIST)
    private Animal animalPrefer;

    private String cause;

    public PrefferedAnimals(String cause) {
        this.cause = cause;
    }

    public PrefferedAnimals() {

    }

    public Long getId_prefferedanimal() {
        return id_prefferedanimal;
    }

    public void setId_prefferedanimal(Long id_prefferedanimal) {
        this.id_prefferedanimal = id_prefferedanimal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Animal getAnimal() {
        return animalPrefer;
    }

    public void setAnimal(Animal animal) {
        this.animalPrefer = animal;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
