package com.example.expensetracker.services.interfaces;

import com.example.expensetracker.data.models.Month;

import java.util.List;

public interface MonthService {

    Month getOrCreateMonth (String monthName);

    List<Month> getAllMonths();
}
