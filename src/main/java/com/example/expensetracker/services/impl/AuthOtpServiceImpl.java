package com.example.expensetracker.services.impl;

import com.example.expensetracker.data.models.AuthOtp;
import com.example.expensetracker.data.repositories.AuthOtpRepository;
import com.example.expensetracker.exception.OtpValidationException;
import com.example.expensetracker.services.interfaces.AuthOtpService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.expensetracker.general.Message.AUTH_OTP_NOT_VALID;

@Service
@AllArgsConstructor
public class AuthOtpServiceImpl implements AuthOtpService {

    private final AuthOtpRepository authOtpRepository;


    @Override
    public void saveOtp(AuthOtp authOtp) {
        authOtpRepository.save(authOtp);
    }

    @Override
    public Optional<AuthOtp> checkOtp(String otp) {
        return authOtpRepository.findByOtp(otp);
    }

    @Override
    public void setConfirmedAt(String otp) {

        if (isValidOtp(otp)) {
            AuthOtp authOtp = authOtpRepository.findByOtp(otp)
                    .orElseThrow(() -> new OtpValidationException(AUTH_OTP_NOT_VALID));

            authOtp.setConfirmedAt(LocalDateTime.now());
            saveOtp(authOtp);
        }
    }


    private boolean isValidOtp(String otp){
       return authOtpRepository.findByOtp(otp).isPresent();
    }
}
