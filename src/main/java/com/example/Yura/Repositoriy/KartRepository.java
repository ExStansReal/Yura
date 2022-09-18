package com.example.Yura.Repositoriy;

import com.example.Yura.models.Kart;
import com.example.Yura.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KartRepository extends CrudRepository<Kart,Long> {

    public List<Kart> findByPersonalkey(Integer key);
    public List<Kart> findByPersonalkeyContains(Integer key);
}
