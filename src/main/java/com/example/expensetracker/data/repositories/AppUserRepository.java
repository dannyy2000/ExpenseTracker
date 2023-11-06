package com.example.expensetracker.data.repositories;

import com.example.expensetracker.data.models.AppUser;
import com.example.expensetracker.data.models.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {

   Optional <AppUser> findUserByEmail(String email);

   AppUser findIncomeById(Long id);


}
