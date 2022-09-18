package com.example.Yura.controllers;


import com.example.Yura.Repositoriy.PostRepository;
import com.example.Yura.Repositoriy.UserRepository;
import com.example.Yura.models.Post;
import com.example.Yura.models.Role;
import com.example.Yura.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Controller
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/registration")
    public String reg_view(Model model)
    {

        return "registration";
    }
    public static int size(Iterable data) {

        if (data instanceof Collection) {
            return ((Collection) data).size();
        }
        int counter = 0;
        for (Object i : data) {
            counter++;
        }
        return counter;
    }
    @PostMapping("/registration")
    public String reg_action(@RequestParam(value = "username") String username,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "surname") String surname,
                             @RequestParam(value = "patronymec") String patr,Model model)
    {
        User user = new User(username,password,name,surname,patr);
        List <User> userFromDB = userRepository.findByUsername(user.getUsername());
        if(!userFromDB.isEmpty()) {
            if (userFromDB.get(0) != null) {
                model.addAttribute(
                        "error",
                        "Такой пользователь уже существует");
                return "/registration";
            }
        }
        if(user.getName().isEmpty() || user.getPassword().isEmpty() || user.getName().isEmpty() || user.getSurname().isEmpty())
        {
            model.addAttribute(
                    "error",
                    "Какое-то поле не заполненно");
            return "/registration";
        }



        Iterable<Post> posts= postRepository.findAll();
        if(size(posts) == 0)
        {
            Post postFirst = new Post("Пользователь",0);
            user.setPost(postFirst);
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            postRepository.save(postFirst);
        }
        else
        {
            List<Post> postFinded= postRepository.findByName("Пользователь");
            user.setPost(postFinded.get(0));
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }


        return "redirect:/login";
    }


}
