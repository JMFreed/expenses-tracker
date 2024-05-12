package com.jfreed.expensestracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jfreed.expensestracker.entity.Expense;
import com.jfreed.expensestracker.service.ExpenseService;


@Controller
@RequestMapping("/expenses")
public class ExpenseController {
    
    @Autowired
    private ExpenseService service;

    @GetMapping
    public String index(Model model) {
        List<Expense> expenses = service.findAll();
        model.addAttribute("expenses", expenses);
        return "expenses/index";
    }
    
}
