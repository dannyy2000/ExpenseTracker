package com.example.expensetracker.utils;

import java.util.Set;

public class MonthValidator {

    private static final Set<String> VALID_MONTHS = Set.of(
            "January","February","March","April",
            "May","June","July","August","September","October",
            "November","December"

    );

    public static boolean isValidMonth(String month){

        return VALID_MONTHS.stream()
                .anyMatch(validMonth -> validMonth.equalsIgnoreCase(month));
    }
}
