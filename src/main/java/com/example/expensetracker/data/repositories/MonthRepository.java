package com.example.expensetracker.data.repositories;

import com.example.expensetracker.data.models.Month;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MonthRepository extends JpaRepository<Month,Long>{
    Optional <Month> findByName(String month);
}
