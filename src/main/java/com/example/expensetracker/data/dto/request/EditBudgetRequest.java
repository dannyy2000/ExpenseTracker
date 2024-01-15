package com.example.expensetracker.data.dto.request;

import com.example.expensetracker.enums.Categories;
import com.example.expensetracker.enums.Currency;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditBudgetRequest {

    @NotNull(message = "field name cannot be null")
    private BigDecimal budgetAmount;
    @NotNull(message = "field name cannot be null")
    private Currency currency;
    @NotNull(message = "field name cannot be null")
    private Categories category;

}
