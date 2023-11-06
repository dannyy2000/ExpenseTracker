package com.example.expensetracker.services.impl;

import com.example.expensetracker.data.dto.request.CreateBudgetRequest;
import com.example.expensetracker.data.dto.request.EditBudgetRequest;
import com.example.expensetracker.data.models.AppUser;
import com.example.expensetracker.data.models.Budget;
import com.example.expensetracker.data.models.Income;
import com.example.expensetracker.enums.Status;
import com.example.expensetracker.exception.AuthException;
import com.example.expensetracker.exception.BudgetException;
import com.example.expensetracker.general.ApiResponse;
import com.example.expensetracker.services.interfaces.AppUserService;
import com.example.expensetracker.services.interfaces.BudgetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static com.example.expensetracker.general.Message.*;

@Service
@RequiredArgsConstructor
@Slf4j

public class BudgetServiceImpl implements BudgetService {

    private final AppUserService appUserService;

    @Override
    public ApiResponse<?> createBudget(CreateBudgetRequest createBudgetRequest) {
        try{
            Optional<AppUser> optionalAppUser = appUserService.getCurrentUser();
            log.info("This is the user: {}", optionalAppUser);

            if(optionalAppUser.isEmpty()) throw new AuthException(AUTH_FAILED);
            log.info("User is authenticated proceeding with budget creation");

            AppUser currentUser = optionalAppUser.get();
            log.info("This is the current user{}",currentUser.getFirstName());

            BigDecimal currentIncome = getCurrentIncome(currentUser);

            Budget budget = getBudget(createBudgetRequest);

            if(createBudgetRequest.getBudgetAmount().compareTo(currentIncome) > 0){
                return ApiResponse.builder()
                        .message(BUDGET_EXCEEDED)
                        .status(Status.BAD_REQUEST)
                        .build();
            }

            currentUser.getBudgets().add(budget);
            appUserService.saveUser(currentUser);

            return ApiResponse.builder()
                    .message("Your budget has been updated successfully")
                    .status(Status.SUCCESS)
                    .data(budget)
                    .build();
        } catch (AuthException e) {
            return ApiResponse.builder()
                    .message(AUTH_FAILED)
                    .status(Status.UNAUTHORIZED)
                    .build();
        }
    }

    @Override
    public ApiResponse<?> editBudget(Long id, EditBudgetRequest editBudgetRequest) {
        try {
            Optional<AppUser> optionalAppUser = appUserService.getCurrentUser();
            log.info("This is the user: {}", optionalAppUser);

            if(optionalAppUser.isEmpty()) throw new AuthException(AUTH_FAILED);
            log.info("User is authenticated proceeding with budget creation");

            AppUser currentUser = optionalAppUser.get();
            log.info("This is the current user{}",currentUser.getFirstName());

            Optional<Budget> optionalBudget = currentUser.getBudgets()
                    .stream()
                    .filter(income -> income.getId().equals(id))
                    .findFirst();

            if(optionalBudget.isEmpty()){
                throw new BudgetException(BUDGET_NOT_FOUND);
            }

            Budget budget = optionalBudget.get();
            budget.setAmount(editBudgetRequest.getBudgetAmount());
            budget.setCurrency(editBudgetRequest.getCurrency());
            budget.setCategory(editBudgetRequest.getCategory());

            appUserService.saveUser(currentUser);

            return ApiResponse.builder()
                    .status(Status.SUCCESS)
                    .message("Budget updated sucessfully")
                    .data(budget)
                    .build();
        }catch (AuthException | BudgetException error){
            return ApiResponse.builder()
                    .status(Status.UNAUTHORIZED)
                    .message(error.getMessage())
                    .build();
        }
    }

    @Override
    public void deleteBudget(Long id) {
        try{
            Optional<AppUser> optionalAppUser = appUserService.getCurrentUser();
            log.info("This is the user: {}", optionalAppUser);

            if(optionalAppUser.isEmpty()) throw new AuthException(AUTH_FAILED);
            log.info("User is authenticated proceeding with budget creation");

            AppUser currentUser = optionalAppUser.get();
            log.info("This is the current user{}",currentUser.getFirstName());

            Optional<Budget> optionalBudget = currentUser.getBudgets()
                    .stream()
                    .filter(income -> income.getId().equals(id))
                    .findFirst();

            if(optionalBudget.isEmpty()){
                throw new BudgetException(BUDGET_NOT_FOUND);
            }

            currentUser.getBudgets().remove(optionalBudget.get());
            appUserService.saveUser(currentUser);
        }catch (AuthException | BudgetException exception){
            exception.getLocalizedMessage();
        }
    }

    private Budget getBudget(CreateBudgetRequest createBudgetRequest) {

           return Budget.builder()
                    .amount(createBudgetRequest.getBudgetAmount())
                    .currency(createBudgetRequest.getCurrency())
                    .category(createBudgetRequest.getCategory())
                   .startDate(LocalDate.now())
                   .endDate(LocalDate.now().plusDays(30))
                    .build();
    }

    private BigDecimal getCurrentIncome(AppUser currentUser) {
        return  currentUser.getIncomeList()
                .stream()
                .map(Income::getAmount)
                .reduce(BigDecimal.ZERO,BigDecimal::add);

    }
}
