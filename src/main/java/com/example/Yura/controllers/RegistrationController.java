package com.example.Yura.controllers;


import com.example.Yura.Repositoriy.UserRepository;
import com.example.Yura.models.Role;
import com.example.Yura.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String reg_view(Model model)
    {
        return "registration";
    }

    @PostMapping("/registration")
    public String reg_action(User user, Model model)
    {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null)
        {
            model.addAttribute(
                    "error",
                    "Такой пользователь уже существует");
            return "/registration";
        }
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty() )
        {
            model.addAttribute(
                    "error",
                    "Вы не ввели данные");
            return "/registration";
        }

        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login";
    }


}
