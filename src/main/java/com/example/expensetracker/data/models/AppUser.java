package com.example.expensetracker.data.models;

import com.example.expensetracker.enums.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class  AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private Role role;
    private String email;
    private String password;
    private Set<Role> roles;
    private boolean isEnabled;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Income> incomeList;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Budget> budgets;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Expense> expenses;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private Date createdAt;

}
