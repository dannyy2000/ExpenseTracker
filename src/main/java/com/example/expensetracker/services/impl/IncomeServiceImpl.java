package com.example.expensetracker.services.impl;

import com.example.expensetracker.data.dto.request.IncomeRequest;
import com.example.expensetracker.data.models.AppUser;
import com.example.expensetracker.data.models.Income;
import com.example.expensetracker.data.repositories.AppUserRepository;
import com.example.expensetracker.data.repositories.IncomeRepository;
import com.example.expensetracker.enums.Status;
import com.example.expensetracker.exception.AuthException;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;
    private final AppUserService appUserService;

    private final AppUserRepository appUserRepository;
    @Override
    public ApiResponse<?> addIncome(IncomeRequest incomeRequest)  {
        try {
//            Optional<AppUser> optionalAppUser = appUserService.getCurrentUser();
//            log.info("This is the the  user{}",optionalAppUser);
//
//            if (optionalAppUser.isEmpty()) throw new AuthException(AUTH_FAILED);
//
//            log.info("User is authenticated. Proceeding with income addition.");
//
//            AppUser currentUser = optionalAppUser.get();
//            log.info("This is the the current user{}",currentUser.getFirstName());
            AppUser appUser = new AppUser();

            Income income = Income.builder()
                    .amount(incomeRequest.getAmount())
                    .currency(incomeRequest.getCurrency())
                    .date(LocalDate.now())
                    .build();

            log.info("This is the income{}",income);
            if(appUser.getIncomeList() == null){
                appUser.setIncomeList(new ArrayList<>());
            }
            appUser.getIncomeList().add(income);


//            if (currentUser.getIncomeList() == null) {
//                currentUser.setIncomeList(new ArrayList<>());
//            }
//            currentUser.getIncomeList().add(income);
//            appUserRepository.save(income);
            incomeRepository.save(income);

            return ApiResponse.builder()
                    .message("Your income has been added successfully")
                    .status(Status.CREATED)
                    .data(income)
                    .build();
        }catch (AuthException e){
            return ApiResponse.builder()
                    .message(e.getMessage())
                    .status(Status.UNAUTHORIZED)
                    .build();
        }
    }


    @Override
    public ApiResponse<?> editIncome(IncomeRequest incomeRequest) {
        return null;
    }

    @Override
    public void deleteIncome(Long id) {

    }




}
