package com.example.expensetracker.services;

import com.example.expensetracker.data.dto.request.RegisterRequest;
import com.example.expensetracker.data.dto.response.RegisterResponse;

public interface AppUserService {

    RegisterResponse registerUser(RegisterRequest registerRequest);
}
