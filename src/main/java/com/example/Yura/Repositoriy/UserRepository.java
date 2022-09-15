package com.example.Yura.Repositoriy;



import com.example.Yura.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
     User findByUsername(String login);
}
