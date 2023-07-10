package com.example.expensetracker.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Income> incomeList;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Budget> budgets;
    @OneToMany
    private List<Expense> expenses;

}
