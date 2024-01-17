package com.example.expensetracker.controllers;

import com.example.expensetracker.data.dto.request.AddExpenseRequest;
import com.example.expensetracker.data.dto.request.IncomeRequest;
import com.example.expensetracker.general.ApiResponse;
import com.example.expensetracker.services.interfaces.ExpenseService;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor

public class ExpenseController {

     private final ExpenseService expenseService;




    @PostMapping("/add")
    @PermitAll
    public BigDecimal addExpense(@Valid @RequestParam String monthName, @RequestBody AddExpenseRequest addExpenseRequest)  {
        return expenseService.addExpense(monthName, addExpenseRequest);
    }
}
