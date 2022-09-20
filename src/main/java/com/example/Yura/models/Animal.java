package com.example.Yura.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_animal;



    private String name, gender;


    private double weight;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private TypeOfAnimal type;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private MealPlan meal;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Cell cell;

    @OneToMany (mappedBy = "animalPrefer", fetch = FetchType.EAGER)
    private Collection<PrefferedAnimals> animals;

    public Animal(String name, String gender, double weight) {
        this.name = name;
        this.gender = gender;
        this.weight = weight;
    }

    public Animal() {

    }

    public Long getId_animal() {
        return id_animal;
    }

    public void setId_animal(Long id_animal) {
        this.id_animal = id_animal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public TypeOfAnimal getType() {
        return type;
    }

    public void setType(TypeOfAnimal type) {
        this.type = type;
    }

    public MealPlan getMeal() {
        return meal;
    }

    public void setMeal(MealPlan meal) {
        this.meal = meal;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
