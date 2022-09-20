package com.example.Yura.controllers;

import com.example.Yura.Repositoriy.*;
import com.example.Yura.models.Animal;
import com.example.Yura.models.Post;
import com.example.Yura.models.User;
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
@RequestMapping("/Animal")
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private MealPlanRepository mealPlanRepository;

    @Autowired
    private CellRepository cellRepository;

    @Autowired
    private TypeOfAnimalRepository typeOfAnimalRepository;

    @GetMapping("/")
    public  String Index(Model model){
        Iterable<Animal> bananas =  animalRepository.findAll();
        model.addAttribute("animals", bananas);
        return "Animal/index";
    }

    @GetMapping("/addView")
    public  String addView(Model model){

        model.addAttribute("animal",new Animal());
        model.addAttribute("plans",mealPlanRepository.findAll());
        model.addAttribute("cell",cellRepository.findAll());
        model.addAttribute("type",typeOfAnimalRepository.findAll());
        return "Animal/add";
    }


    @PostMapping("/add")
    public  String add(
            @ModelAttribute("animal") @Valid Animal newOne,
            BindingResult bind,
            Model model,
            @RequestParam String mealPlan, @RequestParam String cell, @RequestParam String type) {



        if(newOne.getName().isEmpty() || newOne.getGender().isEmpty() || mealPlan.isEmpty() || cell.isEmpty() || type.isEmpty())
        {
            model.addAttribute("error","Вы не ввели какие-то данные");
            model.addAttribute("animal",newOne);
            model.addAttribute("plans",mealPlanRepository.findAll());
            model.addAttribute("cell",cellRepository.findAll());
            model.addAttribute("type",typeOfAnimalRepository.findAll());
            return "Animal/add";
        }




        newOne.setMeal(mealPlanRepository.findByTimefrom(mealPlan).get(0));
        newOne.setCell(cellRepository.findByName(cell).get(0));
        newOne.setType(typeOfAnimalRepository.findByName(type).get(0));

        animalRepository.save(newOne);
        return "redirect:/Animal/";
    }

    @GetMapping("/edit/{id}")
    public  String edit(@PathVariable("id")Long id, Model model
    )
    {
        if(!animalRepository.existsById(id))
        {
            return "redirect:/Animal/";
        }
        Optional<Animal> news = animalRepository.findById(id);
        ArrayList<Animal> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("animal",newArrayList.get(0));

        model.addAttribute("plans",mealPlanRepository.findAll());
        model.addAttribute("cell",cellRepository.findAll());
        model.addAttribute("type",typeOfAnimalRepository.findAll());

        return "Animal/Edit";
    }
    @PostMapping("/edit/{id}")
    public  String editNews(
            @PathVariable("id")Long id,
            @Valid Animal animal,  Model model ,
            @RequestParam String typeAnimal, @RequestParam String cellType, @RequestParam String mealPlanT
    )
    {

        if(!animalRepository.existsById(id))
        {
            return "redirect:/Animal/";
        }
        if(animal.getName().isEmpty() || animal.getGender().isEmpty() || typeAnimal.isEmpty() || cellType.isEmpty() || mealPlanT.isEmpty())
        {
            model.addAttribute("error","Вы не ввели какие-то данные");
            model.addAttribute("animal",animal);
            model.addAttribute("plans",mealPlanRepository.findAll());
            model.addAttribute("cell",cellRepository.findAll());
            model.addAttribute("type",typeOfAnimalRepository.findAll());
            return "Animal/Edit";
        }

        animal.setMeal(mealPlanRepository.findByTimefrom(mealPlanT).get(0));
        animal.setCell(cellRepository.findByName(cellType).get(0));
        animal.setType(typeOfAnimalRepository.findByName(typeAnimal).get(0));

        animal.setId_animal(id);
        animalRepository.save(animal);
        return "redirect:/Animal/";
    }

    @GetMapping("/search")
    public  String searchMetrhod(
            @RequestParam("name") String name,
            Model model)
    {
        if(name.isEmpty())
        {
            Iterable<Animal> bananas =  animalRepository.findAll();
            model.addAttribute("animals", bananas);
            return "Animal/index";
        }
        else {
            List<Animal> bananasList = animalRepository.findByName(name);
            model.addAttribute("animals", bananasList);
            return "/Animal/index";
        }
    }
    @GetMapping("/searchContaints")
    public  String searchMetrhodContains(
            @RequestParam("name") String name,
            Model model)
    {
        if(name.isEmpty())
        {
            Iterable<Animal> bananas =  animalRepository.findAll();
            model.addAttribute("animals", bananas);
            return "Animal/index";
        }
        else {
            List<Animal> bananasList = animalRepository.findByNameContains(name);
            model.addAttribute("animals", bananasList);
            return "/Animal/index";
        }
    }

    @GetMapping("/{id}")
    public  String read(
            @PathVariable("id")Long id,
            Model model)
    {
        Optional<Animal> news = animalRepository.findById(id);
        ArrayList<Animal> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("animals",newArrayList);

        return "Animal/Info";
    }

    @GetMapping("/del/{id}")
    public  String del(
            @PathVariable("id")Long id)
    {
        Animal news = animalRepository.findById(id).orElseThrow();
        animalRepository.delete(news);
        return "redirect:/Animal/";
    }
}
