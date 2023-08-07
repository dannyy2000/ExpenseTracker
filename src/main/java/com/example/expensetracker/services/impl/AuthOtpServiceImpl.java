package com.example.expensetracker.services.impl;

import com.example.expensetracker.data.dto.request.VerifyOtpRequest;
import com.example.expensetracker.data.dto.response.GenerateOtpResponse;
import com.example.expensetracker.data.dto.response.VerifyOtpResponseDto;
import com.example.expensetracker.data.models.AuthOtp;
import com.example.expensetracker.data.repositories.AuthOtpRepository;
import com.example.expensetracker.enums.Status;
import com.example.expensetracker.exception.OtpGenerationException;
import com.example.expensetracker.exception.OtpNotFoundException;
import com.example.expensetracker.exception.OtpValidationException;
import com.example.expensetracker.general.Message;
import com.example.expensetracker.services.interfaces.AuthOtpService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import static com.example.expensetracker.general.Message.*;

@Service
@AllArgsConstructor
public class AuthOtpServiceImpl implements AuthOtpService {

    private final AuthOtpRepository authOtpRepository;


    @Override
    public void saveOtp(AuthOtp authOtp) {
        authOtpRepository.save(authOtp);
    }

    @Override
    public GenerateOtpResponse generateOtp() {
        try{
            String otpGenerated = generateRandomDigits();
            AuthOtp otp = new AuthOtp();

            otp.setOtpValue(otpGenerated);

            otp.setExpiryTime(LocalDateTime.now().plusMinutes(20));
            saveOtp(otp);

            return new GenerateOtpResponse(
                    "Otp generated successfully",
                    Status.SUCCESS,
                    otp.getOtpValue(),
                    otp.getId());

        }catch (OtpGenerationException e){
            return new GenerateOtpResponse(
                    e.getLocalizedMessage(),
                    Status.BAD_REQUEST,
                    null,
                    null
            );
        }

    }

    @Override
    public VerifyOtpResponseDto verifyOtp(VerifyOtpRequest verifyOtpRequest) {
        try {

            boolean verified = checkIfOtpExists(verifyOtpRequest.getOtp());

            if (!verified) {
                throw new OtpValidationException(AUTH_OTP_NOT_VERIFIED);
            }

            Optional<AuthOtp> authOtp = authOtpRepository.findByOtpValue(verifyOtpRequest.getOtp());

            if (authOtp.isEmpty()) {
                throw new OtpNotFoundException(AUTH_OTP_NOT_FOUND);
            }

            AuthOtp otp = authOtp.get();

            if (otp.isExpired()) {
                throw new OtpValidationException(AUTH_OTP_EXPIRED);
            }

            if (otp.isUsed()) {
                throw new OtpValidationException(AUTH_OTP_USED);
            }

            otp.setUsed(true);

            return new VerifyOtpResponseDto(
                    "Otp has been verified",
                    Status.SUCCESS,
                    otp.getOtpValue()
            );

        }catch (OtpValidationException | NullPointerException e){
            return new VerifyOtpResponseDto(
                    e.getLocalizedMessage(),
                    Status.INTERNAL_SERVER_ERROR,
                    null
            );
        }

    }


    private String generateRandomDigits() throws OtpGenerationException{

        Random random = new Random();
        int number =  random.nextInt(999999);
        String otp = String.format("%06d",number);

        if(otp.isEmpty() || otp.trim().isEmpty()){
            generateRandomDigits();
        }

        if(checkIfOtpExists(otp)){
            generateRandomDigits();
        }

        return otp;

    }

    private boolean checkIfOtpExists(String otp) {
          if(otp.trim().isEmpty() || otp.isEmpty()){
              throw new OtpGenerationException(AUTH_OTP_NOT_GENERATED);
          }

         Optional<AuthOtp> newOtp = authOtpRepository.findByOtpValue(otp);
          return newOtp.isPresent();
    }


}
