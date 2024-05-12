package com.jfreed.expensestracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfreed.expensestracker.entity.Expense;
import com.jfreed.expensestracker.repository.ExpenseRepository;

@Service
public class ExpenseService {
    
    @Autowired
    private ExpenseRepository repository;

    public List<Expense> findAll() {
        return repository.findAll();
    }

    public List<Expense> findByLabel(String label) {
        return repository.findByLabel(label);
    }

    public Expense save(Expense book) {
        return repository.save(book);
    }
}
