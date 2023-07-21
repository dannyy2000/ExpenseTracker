package com.example.expensetracker.services.interfaces;

import com.example.expensetracker.data.models.AuthOtp;

import java.util.Optional;

public interface AuthOtpService {

    void saveOtp(AuthOtp authOtp);

    Optional<AuthOtp> checkOtp(String otp);

    void setConfirmedAt(String otp);
}
