package com.example.Yura.Repositoriy;

import com.example.Yura.models.Post;

import com.example.Yura.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post,Long> {

    public List<Post> findByName(String name);
    public List<Post> findByNameContains(String name);
}
