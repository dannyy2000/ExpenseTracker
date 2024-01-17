package com.example.expensetracker.data.models;

import com.example.expensetracker.enums.Categories;
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
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private BigDecimal amount;
    private String description;
    private LocalDate date;
    @Enumerated(value=EnumType.STRING)
    private Currency currency;
//    @ManyToOne
//    private Budget budget;
    @ManyToOne
    private Month month;

    @Enumerated(value = EnumType.STRING)
    private Categories categories;
}
