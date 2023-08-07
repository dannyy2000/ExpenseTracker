//package com.example.expensetracker.services.interfaces;
//
//import com.example.expensetracker.data.dto.request.LoginRequest;
//import com.example.expensetracker.data.dto.request.SignUpRequest;
//import com.example.expensetracker.data.dto.response.TokenResponse;
//import com.example.expensetracker.exception.EmailFoundException;
//import com.example.expensetracker.general.ApiResponse;
//import jakarta.validation.constraints.Email;
//
//public interface AuthenticationService {
//
//    ApiResponse requestSignUp(@Email  String email) throws EmailFoundException;
//
//    ApiResponse confirmOtp(@Email String email, String otp);
//
//    ApiResponse completeSignUp(SignUpRequest signUpRequest);
//
//    TokenResponse login(LoginRequest loginRequest);
//
//
//}
