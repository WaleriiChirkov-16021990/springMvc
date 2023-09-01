package org.chirkov.firstSpringMvcProject.controllers;

import jakarta.validation.Valid;
import org.chirkov.firstSpringMvcProject.models.Person;
import org.chirkov.firstSpringMvcProject.services.ItemService;
import org.chirkov.firstSpringMvcProject.services.PeopleService;
import org.chirkov.firstSpringMvcProject.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
//    private final PersonDAO personDAO;
    private final PeopleService peopleService;
    private final PersonValidator personValidator;
    private final ItemService itemService;

    @Autowired
    public PeopleController(PeopleService peopleService, PersonValidator personValidator, ItemService itemService) {
        this.peopleService = peopleService;
//        this.personDAO = personDAO;
        this.personValidator = personValidator;
        this.itemService = itemService;
    }

    @GetMapping
    public String index(Model model) { //получим всех людей из DAO
        model.addAttribute("people", peopleService.findAll());
//        itemService.findByOwner(peopleService.findAll().get(0));
//        itemService.findByItemName("Iphone");
//        peopleService.test();
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) { //получаем id из GET запроса и
        // передаем его в сигнатуру метода
        //в теле метода получим 1 человека по его ИД из ДАО и дадим его в представление
        model.addAttribute("person", peopleService.findOne(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/new";
        }
//        personDAO.save(person);
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
//        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }


    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
//        personDAO.update(id, person);
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
//        personDAO.delete(id);
        peopleService.delete(id);
        return "redirect:/people";
    }

//    public PersonDAO getPersonDAO() {
//        return personDAO;
//    }

    public PersonValidator getPersonValidator() {
        return personValidator;
    }
}
