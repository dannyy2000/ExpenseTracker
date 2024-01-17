package com.example.expensetracker.services.interfaces;

import com.example.expensetracker.data.dto.request.AddExpenseRequest;

import java.math.BigDecimal;

public interface ExpenseService {

    BigDecimal addExpense(String monthName, AddExpenseRequest addExpenseRequest);
}
