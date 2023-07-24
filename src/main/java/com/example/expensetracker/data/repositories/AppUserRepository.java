package com.example.expensetracker.data.repositories;

import com.example.expensetracker.data.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    AppUser findUserByEmail(String email);
}
