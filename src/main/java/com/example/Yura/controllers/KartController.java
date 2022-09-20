package com.example.Yura.controllers;



import com.example.Yura.Repositoriy.KartRepository;
import com.example.Yura.Repositoriy.UserRepository;
import com.example.Yura.models.User;
import com.example.Yura.models.Kart;
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
@RequestMapping("/Kart")
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
public class KartController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KartRepository kartRepository;

    @GetMapping("/")
    public  String Index(Model model){
        Iterable<Kart> bananas =  kartRepository.findAll();
        model.addAttribute("karts", bananas);
        return "Kart/index";
    }

    @GetMapping("/addView")
    public  String addView(Model model){

        model.addAttribute("kart",new Kart());
        model.addAttribute("users",userRepository.findAll());
        return "Kart/add";
    }


    @PostMapping("/add")
    public  String add(
            @ModelAttribute("kart") @Valid Kart newOne, BindingResult bind,@RequestParam String user,
            Model model){

        if(bind.hasErrors()) {
            model.addAttribute("users",userRepository.findAll());
            return "Kart/add";
        }
        if(user.isEmpty()) {
            model.addAttribute("users",userRepository.findAll());
            return "Kart/add";
        }

        Iterable<Kart> karts =  kartRepository.findAll();


        for (Kart find_kart : karts)
        {
            if(find_kart.getUserUserName().equals(user))
            {
                model.addAttribute("error","У этого пользователя уже есть карта!");
                model.addAttribute("kart",new Kart());
                model.addAttribute("users",userRepository.findAll());
                return "Kart/add";
            }
        }

        List <User> user1 = userRepository.findByUsername(user);



        newOne.setUser(user1.get(0));
        newOne.setAllowed(true);

        kartRepository.save(newOne);
        return "redirect:/Kart/";
    }

    @GetMapping("/edit/{id}")
    public  String edit(@PathVariable("id")Long id, Model model
    )
    {
        if(!kartRepository.existsById(id))
        {
            return "redirect:/Kart/";
        }
        Optional<Kart> news = kartRepository.findById(id);
        ArrayList<Kart> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("kart",newArrayList.get(0));

        Iterable<User> address = userRepository.findAll();
        model.addAttribute("users",address);

        return "Kart/Edit";
    }
    @PostMapping("/edit/{id}")
    public  String editNews(
            @PathVariable("id")Long id,
            @Valid Kart kart,BindingResult bind, Model model
    )
    {

        if(bind.hasErrors()) {
            Optional<Kart> news = kartRepository.findById(id);
            ArrayList<Kart> newArrayList = new ArrayList<>();
            news.ifPresent(newArrayList::add);
            model.addAttribute("kart",newArrayList.get(0));

            Iterable<User> address = userRepository.findAll();
            model.addAttribute("users",address);
            return "Kart/Edit";
        }

        Kart kart_Find = kartRepository.findById(id).orElseThrow();

        List<User> user_find= userRepository.findByUsername(kart_Find.getUserUserName());



        if(!kartRepository.existsById(id))
        {
            return "redirect:/Kart/";
        }



        kart.setUser(user_find.get(0));
        kart.setID_Kart(id);


        kartRepository.save(kart);
        return "redirect:/Kart/";
    }

    @GetMapping("/search")
    public  String searchMetrhod(
            @RequestParam("personal_key") String key,
            Model model)
    {
        if(key.isEmpty())
        {
            Iterable<Kart> bananas =  kartRepository.findAll();
            model.addAttribute("karts", bananas);
            return "Kart/index";
        }
        else {
            List<Kart> bananasList = kartRepository.findByPersonalkey(key);
            model.addAttribute("karts", bananasList);
            return "/Kart/index";
        }
    }
    @GetMapping("/searchContaints")
    public  String searchMetrhodContains(
            @RequestParam("personal_key") String key,
            Model model)
    {
        if(key.isEmpty())
        {
            Iterable<Kart> bananas =  kartRepository.findAll();
            model.addAttribute("karts", bananas);
            return "Kart/index";
        }
        else {
            List<Kart> bananasList = kartRepository.findByPersonalkeyContains(key);
            model.addAttribute("karts", bananasList);
            return "/Kart/index";
        }
    }

    @GetMapping("/{id}")
    public  String read(
            @PathVariable("id")Long id,
            Model model)
    {
        Optional<Kart> news = kartRepository.findById(id);
        ArrayList<Kart> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("kart",newArrayList);

        return "Kart/Info";
    }

    @GetMapping("/del/{id}")
    public  String del(
            @PathVariable("id")Long id)
    {
        Kart news = kartRepository.findById(id).orElseThrow();
        kartRepository.delete(news);
        return "redirect:/Kart/";
    }
}
