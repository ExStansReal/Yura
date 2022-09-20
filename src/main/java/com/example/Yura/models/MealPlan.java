package com.example.Yura.models;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
public class MealPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mealplan;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private FoodType type;


    @OneToMany(mappedBy = "meal", fetch = FetchType.EAGER)
    private Collection<Animal> animals;

    @NotEmpty(message = "Время не может быть пустым")
    private String timefrom,timefor;


    public MealPlan(String timefrom, String timefor) {
        this.timefrom = timefrom;
        this.timefor = timefor;
    }

    public MealPlan() {

    }

    public Long getId_mealplan() {
        return id_mealplan;
    }

    public void setId_mealplan(Long id_mealplan) {
        this.id_mealplan = id_mealplan;
    }

    public FoodType getType() {
        return type;
    }

    public void setType(FoodType type) {
        this.type = type;
    }

    public String getTimefrom() {
        return timefrom;
    }

    public void setTimefrom(String timefrom) {
        this.timefrom = timefrom;
    }

    public String getTimefor() {
        return timefor;
    }

    public void setTimefor(String timefor) {
        this.timefor = timefor;
    }
}
