package org.chirkov.firstSpringMvcProject.controllers;

import jakarta.validation.Valid;
import org.chirkov.firstSpringMvcProject.DataAccessObject.PersonDAO;
import org.chirkov.firstSpringMvcProject.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }


    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        personDAO.delete(id);
        return "redirect:/people";
    }

    public PersonDAO getPersonDAO() {
        return personDAO;
    }

}
