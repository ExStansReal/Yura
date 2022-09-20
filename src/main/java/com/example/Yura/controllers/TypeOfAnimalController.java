package com.example.Yura.controllers;


import com.example.Yura.Repositoriy.FoodTypeRepository;
import com.example.Yura.Repositoriy.TypeOfAnimalRepository;
import com.example.Yura.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/AnimalType")
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
public class TypeOfAnimalController {

    @Autowired
    private TypeOfAnimalRepository typeOfAnimalRepository;

    @GetMapping("/")
    public  String Index(Model model){
        Iterable<TypeOfAnimal> bananas =  typeOfAnimalRepository.findAll();
        model.addAttribute("types", bananas);
        return "AnimalType/index";
    }

    @GetMapping("/addView")
    public  String addView(Model model){
        model.addAttribute("type",new TypeOfAnimal());
        return "AnimalType/add";
    }



    @PostMapping("/add")
    public  String add(
            @ModelAttribute("post") @Valid TypeOfAnimal newOne,
            BindingResult bind,
            Model model){

        if(bind.hasErrors())
        {
            model.addAttribute("error","Ой-ой! Вы ввели что-то не корректно! Проверьте что вы ввели");
            model.addAttribute("type",newOne);
            return "AnimalType/add";
        }

        typeOfAnimalRepository.save(newOne);
        return "redirect:/AnimalType/";
    }



    @GetMapping("/edit/{id}")
    public  String edit(@PathVariable("id")Long id, Model model
    )
    {
        if(!typeOfAnimalRepository.existsById(id))
        {
            return "redirect:/AnimalType/";
        }
        Optional<TypeOfAnimal> news = typeOfAnimalRepository.findById(id);
        ArrayList<TypeOfAnimal> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("type",newArrayList.get(0));

        return "AnimalType/Edit";
    }
    @PostMapping("/edit/{id}")
    public  String editNews(
            @PathVariable("id")Long id,
            @ModelAttribute("post") @Valid TypeOfAnimal newOne, BindingResult bind,Model model
    )
    {

        if(!typeOfAnimalRepository.existsById(id))
        {

            return "redirect:/AnimalType/";
        }

        if(bind.hasErrors())
        {
            model.addAttribute("error","Ой-ой! Вы ввели что-то не корректно! Проверьте что вы ввели");
            Optional<TypeOfAnimal> news = typeOfAnimalRepository.findById(id);
            ArrayList<TypeOfAnimal> newArrayList = new ArrayList<>();
            news.ifPresent(newArrayList::add);
            model.addAttribute("type",newArrayList.get(0));
            return "AnimalType/add";
        }

        newOne.setId_typeofanimal(id);

        typeOfAnimalRepository.save(newOne);
        return "redirect:/AnimalType/";
    }

    @GetMapping("/search")
    public  String searchMetrhod(
            @RequestParam("name") String name,
            Model model)
    {
        if(name.isEmpty())
        {
            Iterable<TypeOfAnimal> bananas =  typeOfAnimalRepository.findAll();
            model.addAttribute("types", bananas);
            return "AnimalType/index";
        }
        else {
            List<TypeOfAnimal> bananasList = typeOfAnimalRepository.findByName(name);
            model.addAttribute("types", bananasList);
            return "/AnimalType/index";
        }
    }
    @GetMapping("/searchContaints")
    public  String searchMetrhodContains(
            @RequestParam("name") String name,
            Model model)
    {
        if(name.isEmpty())
        {
            Iterable<TypeOfAnimal> bananas =  typeOfAnimalRepository.findAll();
            model.addAttribute("types", bananas);
            return "AnimalType/index";
        }
        else {
            List<TypeOfAnimal> bananasList = typeOfAnimalRepository.findByNameContains(name);
            model.addAttribute("types", bananasList);
            return "/AnimalType/index";
        }
    }

    @GetMapping("/{id}")
    public  String read(
            @PathVariable("id")Long id,
            Model model)
    {
        Optional<TypeOfAnimal> news = typeOfAnimalRepository.findById(id);
        ArrayList<TypeOfAnimal> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("type",newArrayList);

        return "AnimalType/Info";
    }

    @GetMapping("/del/{id}")
    public  String del(
            @PathVariable("id")Long id)
    {
        TypeOfAnimal news = typeOfAnimalRepository.findById(id).orElseThrow();
        typeOfAnimalRepository.delete(news);
        return "redirect:/AnimalType/";
    }


}
