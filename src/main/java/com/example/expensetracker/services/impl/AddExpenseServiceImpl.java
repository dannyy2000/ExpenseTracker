package com.example.expensetracker.services.impl;

import com.example.expensetracker.data.dto.request.AddExpenseRequest;
import com.example.expensetracker.data.models.AppUser;
import com.example.expensetracker.data.models.Expense;
import com.example.expensetracker.data.models.Month;
import com.example.expensetracker.data.repositories.ExpenseRepository;
import com.example.expensetracker.data.repositories.IncomeRepository;
import com.example.expensetracker.exception.AuthException;
import com.example.expensetracker.exception.ExpenseException;
import com.example.expensetracker.services.interfaces.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static com.example.expensetracker.general.Message.AUTH_FAILED;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddExpenseServiceImpl implements ExpenseService {

    private final MonthService monthService;
    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final BalanceService balanceService;
    private final AppUserService appUserService;
    @Override
    public BigDecimal addExpense(String monthName, AddExpenseRequest addExpenseRequest) {

        Optional<AppUser> optionalAppUser = appUserService.getCurrentUser();
        log.info("This is the the  user{}", optionalAppUser);

        if (optionalAppUser.isEmpty()) throw new AuthException(AUTH_FAILED);

        log.info("User is authenticated. Proceeding with income addition.");

        AppUser currentUser = optionalAppUser.get();
        log.info("This is the the current user{}", currentUser.getFirstName());

        validateExpense(monthName);

        Month month = monthService.getOrCreateMonth(monthName);

        Expense expense = Expense.builder()
                .amount(addExpenseRequest.getAmount())
                .categories(addExpenseRequest.getCategories())
                .currency(addExpenseRequest.getCurrency())
                .month(month)
                .build();

        currentUser.getExpenses().add(expense);
        appUserService.saveUser(currentUser);

        expenseRepository.save(expense);
        return balanceService.calculateBalance(monthName);

    }



   private void validateExpense(String monthName){

        BigDecimal totalIncome = incomeRepository.getTotalIncomeByMonthName(monthName);

        if(totalIncome.compareTo(BigDecimal.ZERO) <= 0){
            throw new ExpenseException("Expense cannot be added without income");
        }
   }


}
