package com.example.expensetracker.services.interfaces;
import com.example.expensetracker.data.dto.request.LoginRequest;
import com.example.expensetracker.data.dto.request.SignUpRequest;
import com.example.expensetracker.data.dto.request.VerifyUserRequest;
import com.example.expensetracker.general.ApiResponse;


public interface AuthenticationService {

    ApiResponse<?> register(SignUpRequest signUpRequest);

    ApiResponse<?>login(LoginRequest loginRequest);

    ApiResponse<?> verifyUser(VerifyUserRequest verifyUserRequest);

}
