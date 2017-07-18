package com.food.delivery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mz on 14/07/17.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("message1", "This is a message from HomeController.");
        return "home";
    }

    @RequestMapping("/admin")
    public String homeAdmin() {
        return "adminHome";
    }

}
