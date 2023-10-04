package com.example.expensetracker.data.repositories;

import com.example.expensetracker.data.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

    void deleteByToken(String token);
}
