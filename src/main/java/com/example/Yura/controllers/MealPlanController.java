package com.example.Yura.controllers;


import com.example.Yura.Repositoriy.FoodTypeRepository;
import com.example.Yura.Repositoriy.KartRepository;
import com.example.Yura.Repositoriy.MealPlanRepository;
import com.example.Yura.Repositoriy.UserRepository;
import com.example.Yura.models.FoodType;
import com.example.Yura.models.Kart;
import com.example.Yura.models.MealPlan;
import com.example.Yura.models.User;
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
@RequestMapping("/MealPlan")
public class MealPlanController {

    @Autowired
    private MealPlanRepository mealPlanRepository;

    @Autowired
    private FoodTypeRepository foodTypeRepository;

    @GetMapping("/")
    public  String Index(Model model){
        Iterable<MealPlan> bananas =  mealPlanRepository.findAll();
        model.addAttribute("meals", bananas);
        return "MealPlan/index";
    }

    @GetMapping("/addView")
    public  String addView(Model model){

        model.addAttribute("meal",new MealPlan());
        model.addAttribute("food",foodTypeRepository.findAll());
        return "MealPlan/add";
    }


    @PostMapping("/add")
    public  String add(
            @ModelAttribute("meal") @Valid MealPlan newOne, BindingResult bind, @RequestParam String name,
            Model model){

        if(bind.hasErrors()) {
                model.addAttribute("food", foodTypeRepository.findAll());
                return "MealPlan/add";

        }
        if(name.isEmpty()) {
            model.addAttribute("food",foodTypeRepository.findAll());
            return "MealPlan/add";
        }

        List <FoodType> foodType = foodTypeRepository.findByName(name);

        newOne.setType(foodType.get(0));
        mealPlanRepository.save(newOne);
        return "redirect:/MealPlan/";
    }

    @GetMapping("/edit/{id}")
    public  String edit(@PathVariable("id")Long id, Model model
    )
    {
        if(!mealPlanRepository.existsById(id))
        {
            return "redirect:/MealPlan/";
        }
        Optional<MealPlan> news = mealPlanRepository.findById(id);
        ArrayList<MealPlan> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("meal",newArrayList.get(0));

        model.addAttribute("food",foodTypeRepository.findAll());

        return "MealPlan/Edit";
    }
    @PostMapping("/edit/{id}")
    public  String editNews(
            @PathVariable("id")Long id,
            @Valid MealPlan kart,BindingResult bind,@RequestParam String name, Model model
    )
    {
        if(bind.hasErrors()) {
        Optional<MealPlan> news = mealPlanRepository.findById(id);
        ArrayList<MealPlan> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("meal",newArrayList.get(0));
        model.addAttribute("food",foodTypeRepository.findAll());
            return "MealPlan/Edit";
        }
        List<FoodType> food_find= foodTypeRepository.findByName(name);

        if(!mealPlanRepository.existsById(id))
        {
            return "redirect:/MealPlan/";
        }


        kart.setType(food_find.get(0));
        kart.setId_mealplan(id);


        mealPlanRepository.save(kart);
        return "redirect:/MealPlan/";
    }

    @GetMapping("/search")
    public  String searchMetrhod(
            @RequestParam("time") String time,
            Model model)
    {
        if(time.isEmpty())
        {
            Iterable<MealPlan> bananas =  mealPlanRepository.findAll();
            model.addAttribute("meals", bananas);
            return "MealPlan/index";
        }
        else {
            List<MealPlan> bananasList = mealPlanRepository.findByTimefrom(time);
            model.addAttribute("meals", bananasList);
            return "/MealPlan/index";
        }
    }
    @GetMapping("/searchContaints")
    public  String searchMetrhodContains(
            @RequestParam("time") String time,
            Model model)
    {
        if(time.isEmpty())
        {
            Iterable<MealPlan> bananas =  mealPlanRepository.findAll();
            model.addAttribute("meals", bananas);
            return "MealPlan/index";
        }
        else {
            List<MealPlan> bananasList = mealPlanRepository.findByTimefromContains(time);
            model.addAttribute("meals", bananasList);
            return "/MealPlan/index";
        }
    }

    @GetMapping("/{id}")
    public  String read(
            @PathVariable("id")Long id,
            Model model)
    {
        Optional<MealPlan> news = mealPlanRepository.findById(id);
        ArrayList<MealPlan> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("meal",newArrayList);

        return "MealPlan/Info";
    }

    @GetMapping("/del/{id}")
    public  String del(
            @PathVariable("id")Long id)
    {
        MealPlan news = mealPlanRepository.findById(id).orElseThrow();
        mealPlanRepository.delete(news);
        return "redirect:/MealPlan/";
    }
}
