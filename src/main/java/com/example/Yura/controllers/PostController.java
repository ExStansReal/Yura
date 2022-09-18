package com.example.Yura.controllers;


import com.example.Yura.Repositoriy.PostRepository;
import com.example.Yura.models.Post;
import com.example.Yura.models.Role;
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
@RequestMapping("/Post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public  String Index(Model model){
        Iterable<Post> bananas =  postRepository.findAll();
        model.addAttribute("posts", bananas);
        return "Post/index";
    }
    @GetMapping("/addView")
    public  String addView(Model model){

        model.addAttribute("post",new Post());
        return "Post/add";
    }


    @PostMapping("/add")
    public  String add(
            @ModelAttribute("post") @Valid Post newOne,
            BindingResult bind,
            Model model){

        if(bind.hasErrors())
            return "Post/add";

        postRepository.save(newOne);
        return "redirect:/Post/";
    }

    @GetMapping("/edit/{id}")
    public  String edit(@PathVariable("id")Long id, Model model
    )
    {
        if(!postRepository.existsById(id))
        {
            return "redirect:/Post/";
        }
        Optional<Post> news = postRepository.findById(id);
        ArrayList<Post> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("post",newArrayList.get(0));



        return "Post/Edit";
    }
    @PostMapping("/edit/{id}")
    public  String editNews(
            @PathVariable("id")Long id,
            @ModelAttribute("post") @Valid Post newOne, BindingResult bindingResult, Model model
    )
    {

        if(!postRepository.existsById(id))
        {
            return "redirect:/Post/";
        }
        if(bindingResult.hasErrors())
            return "Post/Edit";

        newOne.setId_ppst(id);

        postRepository.save(newOne);
        return "redirect:/Post/";
    }

    @GetMapping("/search")
    public  String searchMetrhod(
            @RequestParam("name") String name,
            Model model)
    {
        if(name.isEmpty())
        {
            Iterable<Post> bananas =  postRepository.findAll();
            model.addAttribute("posts", bananas);
            return "Post/index";
        }
        else {
            List<Post> bananasList = postRepository.findByName(name);
            model.addAttribute("posts", bananasList);
            return "/Post/index";
        }
    }
    @GetMapping("/searchContaints")
    public  String searchMetrhodContains(
            @RequestParam("name") String name,
            Model model)
    {
        if(name.isEmpty())
        {
            Iterable<Post> bananas =  postRepository.findAll();
            model.addAttribute("posts", bananas);
            return "Post/index";
        }
        else {
            List<Post> bananasList = postRepository.findByNameContains(name);
            model.addAttribute("posts", bananasList);
            return "/Post/index";
        }
    }

    @GetMapping("/{id}")
    public  String read(
            @PathVariable("id")Long id,
            Model model)
    {
        Optional<Post> news = postRepository.findById(id);
        ArrayList<Post> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("posts",newArrayList);

        return "Post/Info";
    }

    @GetMapping("/del/{id}")
    public  String del(
            @PathVariable("id")Long id)
    {
        Post news = postRepository.findById(id).orElseThrow();
        postRepository.delete(news);
        return "redirect:/Post/";
    }


}
