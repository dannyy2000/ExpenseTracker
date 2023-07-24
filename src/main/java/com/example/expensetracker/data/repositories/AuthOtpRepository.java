package com.example.expensetracker.data.repositories;

import com.example.expensetracker.data.models.AuthOtp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthOtpRepository extends JpaRepository<AuthOtp,Long> {

    Optional<AuthOtp> findByOtpValue(String otpValue);
}
