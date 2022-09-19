package com.example.Yura.Repositoriy;

import com.example.Yura.models.Kart;
import com.example.Yura.models.MealPlan;
import com.example.Yura.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MealPlanRepository extends CrudRepository<MealPlan,Long> {

    public List<MealPlan> findByTimefrom(String time);
    public List<MealPlan> findByTimefromContains(String time);
}
