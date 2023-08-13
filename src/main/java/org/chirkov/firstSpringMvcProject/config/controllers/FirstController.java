package org.chirkov.firstSpringMvcProject.config.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {
//    @GetMapping("/hello")
//    public String helloPage(HttpServletRequest request) {
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        System.out.printf("Привет, " + name + " " + surname);
//        return "first/hello";
//    }

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name",required = false)  String name,
                            @RequestParam(value = "surname",required = false)  String surname,
                            Model model) {
//        System.out.printf("Привет, " + name + " " + surname + "\n");

        model.addAttribute("message","Hello, " + name + " " + surname + "!");
        return "first/hello";
    }

    @GetMapping("/calc")
    public String calc(@RequestParam(value = "b", required = false) int b,
                       @RequestParam(value = "a", required = false) int a,
                       @RequestParam(value = "action", required = false) String action,
                       Model model) {
        double result;
        String actions = null;
        switch (action) {
            case "multiplication":
                result = a * b;
                actions = " * ";
                break;
            case "division":
                result = a / (double) b;
                actions = " / ";
                break;
            case "subtraction":
                result = a - b;
                actions = " - ";
                break;
            case "addition":
                result = a + b;
                actions = " + ";
                break;
            default:
                result = 0;
                break;
        }
        model.addAttribute("message", a + actions + b + " = " + result + ";");
        return "first/calculater";
    }
    @GetMapping("/bye")
    public String goodbye() {
        return "first/goodbye";
    }

}
