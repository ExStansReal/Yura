package com.example.Yura.controllers;


import com.example.Yura.Repositoriy.CellTypeRepository;
import com.example.Yura.Repositoriy.FoodTypeRepository;
import com.example.Yura.Repositoriy.PostRepository;
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
@RequestMapping("/CellType")
public class CellTypeController {

    @Autowired
    private CellTypeRepository cellTypeRepository;

    @GetMapping("/")
    public  String Index(Model model){
        Iterable<CellType> bananas =  cellTypeRepository.findAll();
        model.addAttribute("types", bananas);
        return "CellType/index";
    }

    @GetMapping("/addView")
    public  String addView(Model model){

        model.addAttribute("type",new CellType());
        return "CellType/add";
    }


    @PostMapping("/add")
    public  String add(
            @ModelAttribute("post") @Valid CellType newOne,

            Model model){

        if(newOne.getClimat().isEmpty() || newOne.getName().isEmpty() || newOne.getTemperature().toString().isEmpty())
        {
            model.addAttribute(
                    "error",
                    "Какое-то поле не заполненно");
            model.addAttribute("type",new CellType());
            return "CellType/add";
        }

        cellTypeRepository.save(newOne);
        return "redirect:/CellType/";
    }

    @GetMapping("/edit/{id}")
    public  String edit(@PathVariable("id")Long id, Model model
    )
    {
        if(!cellTypeRepository.existsById(id))
        {
            return "redirect:/CellType/";
        }
        Optional<CellType> news = cellTypeRepository.findById(id);
        ArrayList<CellType> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("type",newArrayList.get(0));



        return "CellType/Edit";
    }
    @PostMapping("/edit/{id}")
    public  String editNews(
            @PathVariable("id")Long id,
            @ModelAttribute("post") @Valid CellType newOne, Model model
    )
    {

        if(newOne.getClimat().isEmpty() || newOne.getName().isEmpty() || newOne.getTemperature().toString().isEmpty())
        {
            model.addAttribute(
                    "error",
                    "Какое-то поле не заполненно");
            Optional<CellType> news = cellTypeRepository.findById(id);
            ArrayList<CellType> newArrayList = new ArrayList<>();
            news.ifPresent(newArrayList::add);
            model.addAttribute("type",newArrayList.get(0));
            return "CellType/Edit";
        }

        if(!cellTypeRepository.existsById(id))
        {
            return "redirect:/CellType/";
        }

        newOne.setId_celltype(id);

        cellTypeRepository.save(newOne);
        return "redirect:/CellType/";
    }

    @GetMapping("/search")
    public  String searchMetrhod(
            @RequestParam("name") String name,
            Model model)
    {
        if(name.isEmpty())
        {
            Iterable<CellType> bananas =  cellTypeRepository.findAll();
            model.addAttribute("types", bananas);
            return "CellType/index";
        }
        else {
            List<CellType> bananasList = cellTypeRepository.findByName(name);
            model.addAttribute("types", bananasList);
            return "/CellType/index";
        }
    }
    @GetMapping("/searchContaints")
    public  String searchMetrhodContains(
            @RequestParam("name") String name,
            Model model)
    {
        if(name.isEmpty())
        {
            Iterable<CellType> bananas =  cellTypeRepository.findAll();
            model.addAttribute("types", bananas);
            return "CellType/index";
        }
        else {
            List<CellType> bananasList = cellTypeRepository.findByNameContains(name);
            model.addAttribute("types", bananasList);
            return "/CellType/index";
        }
    }

    @GetMapping("/{id}")
    public  String read(
            @PathVariable("id")Long id,
            Model model)
    {
        Optional<CellType> news = cellTypeRepository.findById(id);
        ArrayList<CellType> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("type",newArrayList);

        return "CellType/Info";
    }

    @GetMapping("/del/{id}")
    public  String del(
            @PathVariable("id")Long id)
    {
        CellType news = cellTypeRepository.findById(id).orElseThrow();
        cellTypeRepository.delete(news);
        return "redirect:/CellType/";
    }
}
