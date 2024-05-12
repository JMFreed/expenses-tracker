package com.jfreed.expensestracker.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "expenses")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    
    @Id
    private UUID id;

    private String label;

    private Double amount;
}
