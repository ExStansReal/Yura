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
@RequestMapping("/Cell")
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
public class CellController {

    @Autowired
    private CellTypeRepository cellTypeRepository;
    @Autowired
    private ZooparkRepository zooparkRepository;

    @Autowired
    private CellRepository cellRepository;

    @GetMapping("/")
    public  String Index(Model model){
        Iterable<Cell> bananas =  cellRepository.findAll();
        model.addAttribute("cells", bananas);
        return "Cell/index";
    }




    @GetMapping("/addView")
    public  String addView(Model model){
        model.addAttribute("cell", new Cell());
        model.addAttribute("zooparks", zooparkRepository.findAll());
        model.addAttribute("cellType", cellTypeRepository.findAll());

        return "Cell/add";

    }


    @PostMapping("/add")
    public  String add(
            @ModelAttribute("kart") @Valid Cell newOne, @RequestParam String zooparkSelected, @RequestParam String type,
            Model model){

        if(newOne.getSize() == null || newOne.getName().isEmpty() || zooparkSelected.isEmpty() || type.isEmpty())
        {
            model.addAttribute(
                    "error",
                    "Какое-то поле не заполненно");
            model.addAttribute("cell",new Cell());
            model.addAttribute("zooparks", zooparkRepository.findAll());
            model.addAttribute("cellType", cellTypeRepository.findAll());
            return "Cell/add";
        }



            newOne.setZoopark(zooparkRepository.findByName(zooparkSelected).get(0));
            newOne.setCellType(cellTypeRepository.findByName(type).get(0));


        cellRepository.save(newOne);
        return "redirect:/Cell/";
    }



    @GetMapping("/edit/{id}")
    public  String edit(@PathVariable("id")Long id, Model model
    )
    {
        if(!cellRepository.existsById(id))
        {
            return "redirect:/Cell/";
        }
        Optional<Cell> news = cellRepository.findById(id);
        ArrayList<Cell> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("cell",newArrayList.get(0));

        model.addAttribute("zooparks", zooparkRepository.findAll());
        model.addAttribute("cellType", cellTypeRepository.findAll());

        return "Cell/Edit";
    }
    @PostMapping("/edit/{id}")
    public  String editNews(
            @PathVariable("id")Long id,
            @Valid Cell cell,@RequestParam String park, @RequestParam String type, Model model
    )
    {

        if(cell.getSize() == null || cell.getName().isEmpty() || park.isEmpty() || type.isEmpty())
        {
            model.addAttribute(
                    "error",
                    "Какое-то поле не заполненно");
            model.addAttribute("cell",new Cell());
            Optional<Cell> news = cellRepository.findById(id);
            ArrayList<Cell> newArrayList = new ArrayList<>();
            news.ifPresent(newArrayList::add);
            model.addAttribute("cell",newArrayList.get(0));

            model.addAttribute("zooparks", zooparkRepository.findAll());
            model.addAttribute("cellType", cellTypeRepository.findAll());

            return "Cell/Edit";
        }


        if(!cellRepository.existsById(id))
        {
            return "redirect:/Cell/";
        }


        List <Zoopark> zooFinded = zooparkRepository.findByName(park);
        List <CellType> typeFinded = cellTypeRepository.findByName(type);

        cell.setZoopark(zooFinded.get(0));
        cell.setCellType(typeFinded.get(0));
        cell.setId_cell(id);


        cellRepository.save(cell);
        return "redirect:/Cell/";
    }

    @GetMapping("/search")
    public  String searchMetrhod(
            @RequestParam("name") String name,
            Model model)
    {
        if(name.isEmpty())
        {
            Iterable<Cell> bananas =  cellRepository.findAll();
            model.addAttribute("cells", bananas);
            return "Cell/index";
        }
        else {
            List<Cell> bananasList = cellRepository.findByName(name);
            model.addAttribute("cells", bananasList);
            return "/Cell/index";
        }
    }
    @GetMapping("/searchContaints")
    public  String searchMetrhodContains(
            @RequestParam("name") String name,
            Model model)
    {
        if(name.isEmpty())
        {
            Iterable<Cell> bananas =  cellRepository.findAll();
            model.addAttribute("cells", bananas);
            return "Cell/index";
        }
        else {
            List<Cell> bananasList = cellRepository.findByNameContains(name);
            model.addAttribute("cells", bananasList);
            return "/Cell/index";
        }
    }

    @GetMapping("/{id}")
    public  String read(
            @PathVariable("id")Long id,
            Model model)
    {
        Optional<Cell> news = cellRepository.findById(id);
        ArrayList<Cell> newArrayList = new ArrayList<>();
        news.ifPresent(newArrayList::add);
        model.addAttribute("cell",newArrayList);

        return "Cell/Info";
    }

    @GetMapping("/del/{id}")
    public  String del(
            @PathVariable("id")Long id)
    {
        Cell news = cellRepository.findById(id).orElseThrow();
        cellRepository.delete(news);
        return "redirect:/Cell/";
    }


}
