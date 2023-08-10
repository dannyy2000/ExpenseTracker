package com.example.expensetracker.data.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
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
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private Date createdAt;

}
