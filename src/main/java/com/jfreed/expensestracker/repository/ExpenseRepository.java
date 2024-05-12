package com.jfreed.expensestracker.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jfreed.expensestracker.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
    
    List<Expense> findByLabel(String label);
}
