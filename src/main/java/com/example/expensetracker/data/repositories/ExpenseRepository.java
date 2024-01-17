package com.example.expensetracker.data.repositories;

import com.example.expensetracker.data.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    BigDecimal getTotalExpenseByMonthName(String monthName);
}
