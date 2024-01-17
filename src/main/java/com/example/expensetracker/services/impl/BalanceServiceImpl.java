package com.example.expensetracker.services.impl;

import com.example.expensetracker.data.repositories.ExpenseRepository;
import com.example.expensetracker.data.repositories.IncomeRepository;
import com.example.expensetracker.services.interfaces.BalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class BalanceServiceImpl implements BalanceService {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    @Override
    public BigDecimal calculateBalance(String monthName) {
        BigDecimal totalIncome = incomeRepository.getTotalIncomeByMonthName(monthName);
        BigDecimal totalExpense = expenseRepository.getTotalExpenseByMonthName(monthName);

        return totalIncome.subtract(totalExpense);
    }
}
