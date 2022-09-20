package com.example.Yura.Repositoriy;

import com.example.Yura.models.Animal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimalRepository extends CrudRepository<Animal,Long> {

public List<Animal> findByName(String name);
public List<Animal> findByNameContains(String name);
}
