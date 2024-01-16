//package com.example.expensetracker.data.models;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.List;
//
//@Entity
//@Builder
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class Category {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;
//    private String name;
//    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
//    private List<Expense> expenses;
//}
