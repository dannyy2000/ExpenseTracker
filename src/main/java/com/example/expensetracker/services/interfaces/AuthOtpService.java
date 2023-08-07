package com.example.expensetracker.services.interfaces;

import com.example.expensetracker.data.dto.request.VerifyOtpRequest;
import com.example.expensetracker.data.dto.response.GenerateOtpResponse;
import com.example.expensetracker.data.dto.response.VerifyOtpResponseDto;
import com.example.expensetracker.data.models.AuthOtp;

import java.util.Optional;

public interface AuthOtpService {

    void saveOtp(AuthOtp authOtp);

   GenerateOtpResponse generateOtp();

   VerifyOtpResponseDto verifyOtp(VerifyOtpRequest verifyOtpRequest);
}
