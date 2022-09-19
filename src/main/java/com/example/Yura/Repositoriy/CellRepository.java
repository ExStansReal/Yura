package com.example.Yura.Repositoriy;

import com.example.Yura.models.Cell;
import com.example.Yura.models.CellType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CellRepository  extends CrudRepository<Cell,Long> {

    public List<Cell> findByName(String name);
    public List<Cell> findByNameContains(String name);
}

