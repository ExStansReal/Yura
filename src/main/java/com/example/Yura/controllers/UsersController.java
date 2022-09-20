package com.example.Yura.controllers;


import com.example.Yura.Repositoriy.PostRepository;
import com.example.Yura.Repositoriy.UserRepository;
import com.example.Yura.models.Post;
import com.example.Yura.models.Role;
import com.example.Yura.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Users")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public  String Index(Model model){
        Iterable<User> bananas =  userRepository.findAll();
        model.addAttribute("users", bananas);
        return "Users/index";
    }

    @GetMapping("/addView")
    public  String addView(Model model){

        model.addAttribute("user",new User());
        return "Users/add";
    }


    @PostMapping("/add")
    public  String add(
            @ModelAttribute("user") @Valid User newOne,
            BindingResult bind,
            Model model){

        if(bind.hasErrors()) {
            model.addAttribute("user",new User());
            return "Users/add";
        }

        List<Post> posts= postRepository.findByName("Пользователь");
        newOne.setActive(true);
        newOne.setPost(posts.get(0));

        userRepository.save(newOne);
        return "redirect:/Users/";
    }

    @GetMapping("/edit/{id}")
    public  String edit(@PathVariable("id")Long id, Model model
    )
    {
        if(!userRepository.existsById(id))
        {
            return "redirect:/Users/";
        }
        Optional<User> news = userRepository.findById(id);
        ArrayList<User> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("user",newArrayList.get(0));

        Iterable<Post> address = postRepository.findAll();
        model.addAttribute("posts",address);

        return "Users/Edit";
    }
    @PostMapping("/edit/{id}")
    public  String editNews(
            @PathVariable("id")Long id,
            @Valid User user, BindingResult bind, Model model , @RequestParam String namePost
    )
    {
        List<Post> posts= postRepository.findByName(namePost);
        User edit_user = userRepository.findById(id).orElseThrow();
        if(!userRepository.existsById(id))
        {

            return "redirect:/Users/";
        }
        if(bind.hasErrors()) {
            Iterable<Post> address = postRepository.findAll();
            model.addAttribute("posts",address);
            return "Users/Edit";
        }
        edit_user.setSurname(user.getSurname());
        edit_user.setName(user.getName());
        edit_user.setPatronymec(user.getPatronymec());
        edit_user.setUsername(user.getUsername());
        edit_user.setPost(posts.get(0));

        userRepository.save(edit_user);
        return "redirect:/Users/";
    }

    @GetMapping("/search")
    public  String searchMetrhod(
            @RequestParam("surname") String Surname,
            Model model)
    {
        if(Surname.isEmpty())
        {
            Iterable<User> bananas =  userRepository.findAll();
            model.addAttribute("users", bananas);
            return "Users/index";
        }
        else {
            List<User> bananasList = userRepository.findBySurname(Surname);
            model.addAttribute("users", bananasList);
            return "/Users/index";
        }
    }
    @GetMapping("/searchContaints")
    public  String searchMetrhodContains(
            @RequestParam("surname") String surname,
            Model model)
    {
        if(surname.isEmpty())
        {
            Iterable<User> bananas =  userRepository.findAll();
            model.addAttribute("users", bananas);
            return "Users/index";
        }
        else {
            List<User> bananasList = userRepository.findBySurnameContains(surname);
            model.addAttribute("users", bananasList);
            return "/Users/index";
        }
    }

    @GetMapping("/{id}")
    public  String read(
            @PathVariable("id")Long id,
            Model model)
    {
        Optional<User> news = userRepository.findById(id);
        ArrayList<User> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("users",newArrayList);

        return "Users/Info";
    }

    @GetMapping("/del/{id}")
    public  String del(
            @PathVariable("id")Long id)
    {
        User news = userRepository.findById(id).orElseThrow();
        userRepository.delete(news);
        return "redirect:/Users/";
    }

    @GetMapping("/edit-role/{id}")
    public  String user_role(@PathVariable("id") Long id, Model model)
    {
        Optional<User> user = userRepository.findById(id);
        ArrayList<User> userArrayList = new ArrayList<>();
        user.ifPresent(userArrayList::add);
        model.addAttribute("one_user",userArrayList);
        model.addAttribute("roles", Role.values());


        return "Users/edit-role";
    }

    @PostMapping("/edit-role")
    public String edit_role(@RequestParam("user_id") User user,@RequestParam("username")
    String username,@RequestParam(name = "roles[]", required = false) String[] roles, Model model)
    {
        user.setUsername(username);
        user.getRoles().clear();
        if(roles != null)
        {
            for(String role_name:roles)
            {
                user.getRoles().add(Role.valueOf(role_name));
            }
        }
        userRepository.save(user);
        return "redirect:/Users/";
    }
}
