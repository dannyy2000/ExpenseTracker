package com.example.expensetracker.services.interfaces;

import java.math.BigDecimal;

public interface BalanceService {

    BigDecimal calculateBalance(String monthName);
}
