package com.example.Yura.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class FoodType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_foodtype;


    private String name;


    @OneToMany (mappedBy = "type", fetch = FetchType.EAGER)
    private Collection<MealPlan> mealPlans;

    public FoodType(String name) {
        this.name = name;
    }

    public FoodType() {

    }

    public Long getId_foodtype() {
        return id_foodtype;
    }

    public void setId_foodtype(Long id_foodtype) {
        this.id_foodtype = id_foodtype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<MealPlan> getMealPlans() {
        return mealPlans;
    }

    public void setMealPlans(Collection<MealPlan> mealPlans) {
        this.mealPlans = mealPlans;
    }
}
