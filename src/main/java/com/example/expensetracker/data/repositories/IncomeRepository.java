package com.example.expensetracker.data.repositories;

import com.example.expensetracker.data.models.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Override
    Optional<Income> findById(Long id);
}
