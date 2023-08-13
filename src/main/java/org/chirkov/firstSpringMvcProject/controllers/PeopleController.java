package org.chirkov.firstSpringMvcProject.controllers;

import org.chirkov.firstSpringMvcProject.DataAccessObject.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) { //получим всех людей из DAO
        model.addAttribute("people", getPersonDAO().index());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) { //получаем id из GET запроса и
        // передаем его в сигнатуру метода
        //в теле метода получим 1 человека по его ИД из ДАО и дадим его в представление
        model.addAttribute("person", getPersonDAO().show(id));
        return "people/show";
    }

    public PersonDAO getPersonDAO() {
        return personDAO;
    }
}
