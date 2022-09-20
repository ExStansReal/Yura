package com.example.Yura.controllers;

import com.example.Yura.Repositoriy.*;
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
@RequestMapping("/PrefferedAnimals")
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
public class PrefferedAnimalsController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private PrefferedAnimalsRepository prefferedAnimals;

    @GetMapping("/")
    public  String Index(Model model){
        Iterable<PrefferedAnimals> bananas =  prefferedAnimals.findAll();
        model.addAttribute("pref", bananas);
        return "PrefferedAnimals/index";
    }




    @GetMapping("/addView")
    public  String addView(Model model){
        model.addAttribute("pref", new PrefferedAnimals());
        model.addAttribute("animal", animalRepository.findAll());
        model.addAttribute("user", userRepository.findAll());

        return "PrefferedAnimals/add";

    }


    @PostMapping("/add")
    public  String add(
            @ModelAttribute("pref") @Valid PrefferedAnimals newOne, @RequestParam String animalChoose, @RequestParam String user,
            Model model){

        if(newOne.getCause().isEmpty() || animalChoose.isEmpty() || user.isEmpty())
        {
            model.addAttribute(
                    "error",
                    "Какое-то поле не заполненно");
            model.addAttribute("pref", new PrefferedAnimals());
            model.addAttribute("animal", animalRepository.findAll());
            model.addAttribute("user", userRepository.findAll());
            return "PrefferedAnimals/add";
        }



        newOne.setUser(userRepository.findBySurname(user).get(0));
        newOne.setAnimal(animalRepository.findByName(animalChoose).get(0));


        prefferedAnimals.save(newOne);
        return "redirect:/PrefferedAnimals/";
    }



    @GetMapping("/edit/{id}")
    public  String edit(@PathVariable("id")Long id, Model model
    )
    {
        if(!prefferedAnimals.existsById(id))
        {
            return "redirect:/PrefferedAnimals/";
        }
        Optional<PrefferedAnimals> news = prefferedAnimals.findById(id);
        ArrayList<PrefferedAnimals> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("pref",newArrayList.get(0));

        model.addAttribute("animal", animalRepository.findAll());
        model.addAttribute("user", userRepository.findAll());

        return "PrefferedAnimals/Edit";
    }
    @PostMapping("/edit/{id}")
    public  String editNews(
            @PathVariable("id")Long id,
            @Valid PrefferedAnimals prefferedAnimal, @RequestParam String animalChoose, @RequestParam String user, Model model
    )
    {

        if(prefferedAnimal.getCause().isEmpty() || animalChoose.isEmpty() || user.isEmpty())
        {
            model.addAttribute(
                    "error",
                    "Какое-то поле не заполненно");
            Optional<PrefferedAnimals> news = prefferedAnimals.findById(id);
            ArrayList<PrefferedAnimals> newArrayList = new ArrayList<>();
            news.ifPresent(newArrayList::add);
            model.addAttribute("pref",newArrayList.get(0));

            model.addAttribute("animal", animalRepository.findAll());
            model.addAttribute("user", userRepository.findAll());

            return "PrefferedAnimals/Edit";
        }


        if(!prefferedAnimals.existsById(id))
        {
            return "redirect:/PrefferedAnimals/";
        }


        prefferedAnimal.setUser(userRepository.findBySurname(user).get(0));
        prefferedAnimal.setAnimal(animalRepository.findByName(animalChoose).get(0));
        prefferedAnimal.setId_prefferedanimal(id);


        prefferedAnimals.save(prefferedAnimal);
        return "redirect:/PrefferedAnimals/";
    }

    @GetMapping("/search")
    public  String searchMetrhod(
            @RequestParam("cause") String cause,
            Model model)
    {
        if(cause.isEmpty())
        {
            Iterable<PrefferedAnimals> bananas =  prefferedAnimals.findAll();
            model.addAttribute("pref", bananas);
            return "PrefferedAnimals/index";
        }
        else {
            List<PrefferedAnimals> bananasList = prefferedAnimals.findByCause(cause);
            model.addAttribute("pref", bananasList);
            return "/PrefferedAnimals/index";
        }
    }
    @GetMapping("/searchContaints")
    public  String searchMetrhodContains(
            @RequestParam("cause") String cause,
            Model model)
    {
        if(cause.isEmpty())
        {
            Iterable<PrefferedAnimals> bananas =  prefferedAnimals.findAll();
            model.addAttribute("pref", bananas);
            return "PrefferedAnimals/index";
        }
        else {
            List<PrefferedAnimals> bananasList = prefferedAnimals.findByCauseContains(cause);
            model.addAttribute("pref", bananasList);
            return "/PrefferedAnimals/index";
        }
    }

    @GetMapping("/{id}")
    public  String read(
            @PathVariable("id")Long id,
            Model model)
    {
        Optional<PrefferedAnimals> news = prefferedAnimals.findById(id);
        ArrayList<PrefferedAnimals> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("pref",newArrayList);

        return "PrefferedAnimals/Info";
    }

    @GetMapping("/del/{id}")
    public  String del(
            @PathVariable("id")Long id)
    {
        PrefferedAnimals news = prefferedAnimals.findById(id).orElseThrow();
        prefferedAnimals.delete(news);
        return "redirect:/PrefferedAnimals/";
    }




}
