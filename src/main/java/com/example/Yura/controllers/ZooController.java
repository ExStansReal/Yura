package com.example.Yura.controllers;


import com.example.Yura.Repositoriy.ZooparkRepository;
import com.example.Yura.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Zoo")
public class ZooController {

    @Autowired
    private ZooparkRepository zooparkRepository;

    @GetMapping("/")
    public  String Index(Model model){
        Iterable<Zoopark> bananas =  zooparkRepository.findAll();
        model.addAttribute("zooparks", bananas);
        return "Zoo/index";
    }
    @GetMapping("/addView")
    public  String addView(Model model){

        model.addAttribute("zoopark",new Zoopark());
        return "Zoo/add";
    }


    @PostMapping("/add")
    public  String add(
            @ModelAttribute("post") @Valid Zoopark newOne,

            Model model){

        if(newOne.getName().isEmpty() || newOne.getAdress().isEmpty())
        {
            model.addAttribute(
                    "error",
                    "Какое-то поле не заполненно");
            model.addAttribute("zoopark",new Zoopark());
            return "Zoo/add";
        }

        zooparkRepository.save(newOne);
        return "redirect:/Zoo/";
    }

    @GetMapping("/edit/{id}")
    public  String edit(@PathVariable("id")Long id, Model model
    )
    {
        if(!zooparkRepository.existsById(id))
        {
            return "redirect:/Zoo/";
        }
        Optional<Zoopark> news = zooparkRepository.findById(id);
        ArrayList<Zoopark> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("zoopark",newArrayList.get(0));



        return "Zoo/Edit";
    }
    @PostMapping("/edit/{id}")
    public  String editNews(
            @PathVariable("id")Long id,
            @ModelAttribute("post") @Valid Zoopark newOne, Model model
    )
    {

        if(newOne.getName().isEmpty() || newOne.getAdress().isEmpty())
        {
            model.addAttribute(
                    "error",
                    "Какое-то поле не заполненно");
            model.addAttribute("zoopark",new Zoopark());
            return "Zoo/Edit";
        }

        if(!zooparkRepository.existsById(id))
        {
            return "redirect:/Zoo/";
        }

        newOne.setId_zoopark(id);

        zooparkRepository.save(newOne);
        return "redirect:/Zoo/";
    }

    @GetMapping("/search")
    public  String searchMetrhod(
            @RequestParam("name") String name,
            Model model)
    {
        if(name.isEmpty())
        {
            Iterable<Zoopark> bananas =  zooparkRepository.findAll();
            model.addAttribute("zooparks", bananas);
            return "Zoo/index";
        }
        else {
            List<Zoopark> bananasList = zooparkRepository.findByName(name);
            model.addAttribute("zooparks", bananasList);
            return "/Zoo/index";
        }
    }
    @GetMapping("/searchContaints")
    public  String searchMetrhodContains(
            @RequestParam("name") String name,
            Model model)
    {
        if(name.isEmpty())
        {
            Iterable<Zoopark> bananas =  zooparkRepository.findAll();
            model.addAttribute("zooparks", bananas);
            return "Zoo/index";
        }
        else {
            List<Zoopark> bananasList = zooparkRepository.findByNameContains(name);
            model.addAttribute("zooparks", bananasList);
            return "/Zoo/index";
        }
    }

    @GetMapping("/{id}")
    public  String read(
            @PathVariable("id")Long id,
            Model model)
    {
        Optional<Zoopark> news = zooparkRepository.findById(id);
        ArrayList<Zoopark> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("zoopark",newArrayList);

        return "Zoo/Info";
    }

    @GetMapping("/del/{id}")
    public  String del(
            @PathVariable("id")Long id)
    {
        Zoopark news = zooparkRepository.findById(id).orElseThrow();
        zooparkRepository.delete(news);
        return "redirect:/Zoo/";
    }
}
