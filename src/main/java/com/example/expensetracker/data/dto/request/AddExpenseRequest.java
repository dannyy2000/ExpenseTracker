package com.example.expensetracker.data.dto.request;

import com.example.expensetracker.enums.Categories;
import com.example.expensetracker.enums.Currency;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddExpenseRequest {

    private BigDecimal amount;
    private Categories categories;
    private Currency currency;
}
