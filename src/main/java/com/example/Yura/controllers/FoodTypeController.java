package com.example.Yura.controllers;


import com.example.Yura.Repositoriy.FoodTypeRepository;
import com.example.Yura.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/FoodType")
public class FoodTypeController {

    @Autowired
    private FoodTypeRepository foodTypeRepository;

    @GetMapping("/")
    public  String Index(Model model){
        Iterable<FoodType> bananas =  foodTypeRepository.findAll();
        model.addAttribute("types", bananas);
        return "FoodType/index";
    }

    @GetMapping("/addView")
    public  String addView(Model model){
        model.addAttribute("type",new FoodType());
        return "FoodType/add";
    }



    @PostMapping("/add")
    public  String add(
            @ModelAttribute("post") @Valid FoodType newOne,
            BindingResult bind,
            Model model){

        if(newOne.getName().isEmpty()) {
            model.addAttribute("type",new FoodType());
            model.addAttribute("error","Вы не заполнили поле");
            return "FoodType/add";
        }

        foodTypeRepository.save(newOne);
        return "redirect:/FoodType/";
    }



    @GetMapping("/edit/{id}")
    public  String edit(@PathVariable("id")Long id, Model model
    )
    {
        if(!foodTypeRepository.existsById(id))
        {
            return "redirect:/FoodType/";
        }
        Optional<FoodType> news = foodTypeRepository.findById(id);
        ArrayList<FoodType> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("type",newArrayList.get(0));



        return "FoodType/Edit";
    }
    @PostMapping("/edit/{id}")
    public  String editNews(
            @PathVariable("id")Long id,
            @ModelAttribute("post") @Valid FoodType newOne, Model model
    )
    {

        if(newOne.getName().isEmpty())
        {
            model.addAttribute(
                    "error",
                    "Поле не заполненно");
            Optional<FoodType> news = foodTypeRepository.findById(id);
            ArrayList<FoodType> newArrayList = new ArrayList<>();
            news.ifPresent(newArrayList::add);
            model.addAttribute("type",newArrayList.get(0));
            return "FoodType/Edit";
        }

        if(!foodTypeRepository.existsById(id))
        {
            return "redirect:/FoodType/";
        }

        newOne.setId_foodtype(id);

        foodTypeRepository.save(newOne);
        return "redirect:/FoodType/";
    }

    @GetMapping("/search")
    public  String searchMetrhod(
            @RequestParam("name") String name,
            Model model)
    {
        if(name.isEmpty())
        {
            Iterable<FoodType> bananas =  foodTypeRepository.findAll();
            model.addAttribute("types", bananas);
            return "FoodType/index";
        }
        else {
            List<FoodType> bananasList = foodTypeRepository.findByName(name);
            model.addAttribute("types", bananasList);
            return "/FoodType/index";
        }
    }
    @GetMapping("/searchContaints")
    public  String searchMetrhodContains(
            @RequestParam("name") String name,
            Model model)
    {
        if(name.isEmpty())
        {
            Iterable<FoodType> bananas =  foodTypeRepository.findAll();
            model.addAttribute("types", bananas);
            return "FoodType/index";
        }
        else {
            List<FoodType> bananasList = foodTypeRepository.findByNameContains(name);
            model.addAttribute("types", bananasList);
            return "/FoodType/index";
        }
    }

    @GetMapping("/{id}")
    public  String read(
            @PathVariable("id")Long id,
            Model model)
    {
        Optional<FoodType> news = foodTypeRepository.findById(id);
        ArrayList<FoodType> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("type",newArrayList);

        return "FoodType/Info";
    }

    @GetMapping("/del/{id}")
    public  String del(
            @PathVariable("id")Long id)
    {
        FoodType news = foodTypeRepository.findById(id).orElseThrow();
        foodTypeRepository.delete(news);
        return "redirect:/FoodType/";
    }


}
