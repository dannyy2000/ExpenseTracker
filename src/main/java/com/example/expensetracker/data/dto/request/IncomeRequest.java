package com.example.expensetracker.data.dto.request;

import com.example.expensetracker.enums.Currency;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IncomeRequest {

    @NotNull(message = "field name cannot be null")
    private BigDecimal amount;
    @NotNull(message = "field name cannot be null")
    private Currency currency;
}
