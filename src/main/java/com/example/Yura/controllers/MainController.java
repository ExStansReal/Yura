package com.example.Yura.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
public class MainController {
    @GetMapping("/")
    public String Main(Model model) {
        return "index";
    }
}
