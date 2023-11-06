package com.example.expensetracker.data.models;

import com.example.expensetracker.enums.Category;
import com.example.expensetracker.enums.Currency;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private BigDecimal amount;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(value = EnumType.STRING)
    private Currency currency;
    @Enumerated(value = EnumType.STRING)
    private Category category;
}
