package com.example.Yura.Repositoriy;



import com.example.Yura.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {

     public List<User> findByUsername(String login);
     public List<User> findBySurname(String login);
     public List<User> findBySurnameContains(String login);
}
