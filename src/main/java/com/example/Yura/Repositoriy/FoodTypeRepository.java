package com.example.Yura.Repositoriy;

import com.example.Yura.models.FoodType;
import com.example.Yura.models.Kart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodTypeRepository extends CrudRepository<FoodType,Long> {

    public List<FoodType> findByName(String name);
    public List<FoodType> findByNameContains(String name);
}
