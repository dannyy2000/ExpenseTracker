package com.example.expensetracker.data.repositories;

import com.example.expensetracker.data.models.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance,Long>{

    Balance findByMonthName(String monthName);


}
