package com.example.expensetracker.services.impl;

import com.example.expensetracker.data.dto.request.IncomeRequest;
import com.example.expensetracker.data.models.AppUser;
import com.example.expensetracker.data.models.Income;
import com.example.expensetracker.data.repositories.AppUserRepository;
import com.example.expensetracker.data.repositories.IncomeRepository;
import com.example.expensetracker.enums.Status;
import com.example.expensetracker.exception.AuthException;
import com.example.expensetracker.exception.IncomeException;
import com.example.expensetracker.general.ApiResponse;
import com.example.expensetracker.services.interfaces.AppUserService;
import com.example.expensetracker.services.interfaces.IncomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static com.example.expensetracker.general.Message.AUTH_FAILED;
import static com.example.expensetracker.general.Message.INCOME_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;
    private final AppUserService appUserService;

    private final AppUserRepository appUserRepository;

    @Override
    public ApiResponse<?> addIncome(IncomeRequest incomeRequest) {
        try {

            Optional<AppUser> optionalAppUser = appUserService.getCurrentUser();
            log.info("This is the the  user{}", optionalAppUser);

            if (optionalAppUser.isEmpty()) throw new AuthException(AUTH_FAILED);

            log.info("User is authenticated. Proceeding with income addition.");

            AppUser currentUser = optionalAppUser.get();
            log.info("This is the the current user{}", currentUser.getFirstName());

            Income income = Income.builder()
                    .amount(incomeRequest.getAmount())
                    .currency(incomeRequest.getCurrency())
                    .date(LocalDate.now())
                    .build();

            log.info("This is the income{}", income);


            if (currentUser.getIncomeList() == null) {
                currentUser.setIncomeList(new ArrayList<>());
            }
            currentUser.getIncomeList().add(income);
            appUserRepository.save(currentUser);
//            incomeRepository.save(income);

            log.info("income is saved");

            return ApiResponse.builder()
                    .message("Your income has been added successfully")
                    .status(Status.CREATED)
                    .data(income)
                    .build();
        } catch (AuthException e) {
            return ApiResponse.builder()
                    .message(e.getMessage())
                    .status(Status.UNAUTHORIZED)
                    .build();
        }
    }

    @Override
    public ApiResponse<?> editIncome(Long incomeId, IncomeRequest incomeRequest) {
        try {
            Optional<AppUser> optionalAppUser = appUserService.getCurrentUser();
            log.info("This is the user: {}", optionalAppUser);

            if (optionalAppUser.isEmpty()) throw new AuthException(AUTH_FAILED);

            log.info("User is authenticated. Proceeding with income editing.");

            AppUser currentUser = optionalAppUser.get();
            log.info("This is the current user: {}", currentUser.getFirstName());

            Optional<Income> optionalIncome = currentUser.getIncomeList()
                    .stream()
                    .filter(income -> income.getId().equals(incomeId))
                    .findFirst();

            if (optionalIncome.isEmpty()) {
                throw new IncomeException(INCOME_NOT_FOUND);
            }

            Income income = optionalIncome.get();
            income.setAmount(incomeRequest.getAmount());
            income.setCurrency(incomeRequest.getCurrency());
            income.setDate(LocalDate.now());

            appUserRepository.save(currentUser);

            log.info("Income is updated");

            return ApiResponse.builder()
                    .message("Your income has been updated successfully")
                    .status(Status.SUCCESS)
                    .data(income)
                    .build();
        } catch (AuthException | IncomeException e) {
            return ApiResponse.builder()
                    .message(e.getMessage())
                    .status(Status.UNAUTHORIZED)
                    .build();
        }
    }


    @Override
    public void deleteIncome(Long id) {
        try {
            Optional<AppUser> optionalAppUser = appUserService.getCurrentUser();

            if (optionalAppUser.isEmpty()) throw new AuthException(AUTH_FAILED);

            AppUser currentUser = optionalAppUser.get();

            Optional<Income> optionalIncome = currentUser.getIncomeList()
                    .stream()
                    .filter(income -> income.getId().equals(id))
                    .findFirst();

            if(optionalIncome.isEmpty()){
                throw new IncomeException(INCOME_NOT_FOUND);
            }
            currentUser.getIncomeList().remove(optionalIncome.get());
            appUserRepository.save(currentUser);

        } catch (AuthException | IncomeException error) {
            error.getLocalizedMessage();
        }

    }
}





