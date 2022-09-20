package com.example.Yura.Repositoriy;

import com.example.Yura.models.PrefferedAnimals;
import com.example.Yura.models.TypeOfAnimal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrefferedAnimalsRepository extends CrudRepository<PrefferedAnimals,Long> {

    public List<PrefferedAnimals> findByCause(String cause);
    public List<PrefferedAnimals> findByCauseContains(String cause);
}