package com.jfreed.expensestracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    
    @GetMapping("/user/home")
    public String userHome() {
        return "home/user";
    }

    @GetMapping("/admin/home")
    public String adminHome() {
        return "home/admin";
    }
    
}
