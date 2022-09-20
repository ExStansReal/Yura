package com.example.Yura.Repositoriy;

import com.example.Yura.models.FoodType;
import com.example.Yura.models.TypeOfAnimal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TypeOfAnimalRepository extends CrudRepository<TypeOfAnimal,Long> {

    public List<TypeOfAnimal> findByName(String name);
    public List<TypeOfAnimal> findByNameContains(String name);
}
