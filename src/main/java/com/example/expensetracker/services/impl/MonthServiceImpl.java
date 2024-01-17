package com.example.expensetracker.services.impl;

import com.example.expensetracker.data.models.Month;
import com.example.expensetracker.data.repositories.MonthRepository;
import com.example.expensetracker.services.interfaces.MonthService;
import com.example.expensetracker.utils.MonthValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonthServiceImpl implements MonthService {

    private final MonthRepository monthRepository;


    @Override
    public Month getOrCreateMonth(String monthName) {
        validateMonthName(monthName);
        return monthRepository.findByName(monthName)
                .orElseGet(()-> monthRepository.save(new Month(monthName)));
    }

    @Override
    public List<Month> getAllMonths() {
        return monthRepository.findAll();
    }


    private void validateMonthName(String monthName){
        if(!MonthValidator.isValidMonth(monthName))throw new IllegalArgumentException("Invalid Month");
}};
