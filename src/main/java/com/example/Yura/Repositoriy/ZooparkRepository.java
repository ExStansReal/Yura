package com.example.Yura.Repositoriy;

import com.example.Yura.models.Kart;
import com.example.Yura.models.Zoopark;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ZooparkRepository extends CrudRepository<Zoopark,Long> {

    public List<Zoopark> findByName(String name);
    public List<Zoopark> findByNameContains(String name);
}