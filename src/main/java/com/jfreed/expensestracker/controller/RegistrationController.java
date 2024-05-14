package com.jfreed.expensestracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jfreed.expensestracker.register.RegistrationRequest;


@Controller
public class RegistrationController {
    
    @GetMapping("/register")
    public String registration(Model model) {
        RegistrationRequest request = new RegistrationRequest();
        model.addAttribute("request", request);
        return "auth/register";
    }
    
}
