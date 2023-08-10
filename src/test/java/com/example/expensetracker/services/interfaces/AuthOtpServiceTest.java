package com.example.expensetracker.services.interfaces;

import com.example.expensetracker.data.dto.request.VerifyOtpRequest;
import com.example.expensetracker.data.dto.response.GenerateOtpResponse;
import com.example.expensetracker.data.dto.response.VerifyOtpResponseDto;
import com.example.expensetracker.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AuthOtpServiceTest {

    @Autowired
    private AuthOtpService authOtpService;

    private VerifyOtpRequest verifyOtpRequest;

    @BeforeEach
    void setUp() {
        verifyOtpRequest = new VerifyOtpRequest();
        verifyOtpRequest.setOtp("3195599");
    }

    @Test
    void testThatOtpCanBeGenerated(){
      GenerateOtpResponse otpResponse = authOtpService.generateOtp();
      assertThat(otpResponse).isNotNull();
      assertThat(otpResponse.getStatus()).isEqualTo(Status.SUCCESS);
      System.out.println(otpResponse.getOtp());
    }

    @Test
    void testThatCorrectOtpCanBeVerified(){
      VerifyOtpResponseDto otpResponse = authOtpService.verifyOtp(verifyOtpRequest);
      assertThat(otpResponse.getStatus()).isEqualTo(Status.SUCCESS);
      assertThat(otpResponse.getMessage()).isEqualTo("Otp has been verified");
    }

    @Test
    void testThatIncorrectOtpCannotBeVerified(){
        VerifyOtpResponseDto otpResponseDto = authOtpService.verifyOtp(verifyOtpRequest);
        assertThat(otpResponseDto.getStatus()).isEqualTo(Status.INTERNAL_SERVER_ERROR);
    }
}