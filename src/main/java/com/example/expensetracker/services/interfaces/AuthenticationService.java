package com.example.expensetracker.services.interfaces;
import com.example.expensetracker.data.dto.request.SignUpRequest;
import com.example.expensetracker.general.ApiResponse;


public interface AuthenticationService {

    ApiResponse<?> register(SignUpRequest signUpRequest);

}
