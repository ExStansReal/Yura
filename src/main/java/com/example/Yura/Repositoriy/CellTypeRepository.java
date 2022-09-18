package com.example.Yura.Repositoriy;

import com.example.Yura.models.CellType;
import com.example.Yura.models.Kart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CellTypeRepository extends CrudRepository<CellType,Long> {

    public List<CellType> findByName(String name);
    public List<CellType> findByNameContains(String name);
}
