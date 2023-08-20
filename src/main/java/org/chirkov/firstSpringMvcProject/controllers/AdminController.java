package org.chirkov.firstSpringMvcProject.controllers;

import org.chirkov.firstSpringMvcProject.DataAccessObject.PersonDAO;
import org.chirkov.firstSpringMvcProject.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import org.springframework.webflow.engine.model.Model;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PersonDAO personDAO;

    @Autowired
    public AdminController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String adminPage(Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("people", personDAO.index());
        return "admin/page";
    }

//    @PostMapping("/add")
    @PatchMapping("/add")
    public String addAdmin(@ModelAttribute("person") Person person) {
        System.out.println(person.getId());
        return "redirect:/people";
    }



}
