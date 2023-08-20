package org.chirkov.firstSpringMvcProject.controllers;

import org.chirkov.firstSpringMvcProject.DataAccessObject.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test-batch-update")
public class BatchController {

    private final PersonDAO personDAO;
    @Autowired
    public BatchController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index() {
        return "batch/index";
    }

    @GetMapping("/witchOut")
    public String witchOutBatchUpdate() {
        personDAO.testMultipleUpdate();
        return "redirect:/people";
    }

    @GetMapping("/witch")
    public String witchOutBatch() {
        personDAO.batchUpdate();
        return "redirect:/people";
    }


    public PersonDAO getPersonDAO() {
        return personDAO;
    }
}
