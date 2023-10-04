package com.example.expensetracker.data.dto.response;

import com.example.expensetracker.enums.Status;
import com.example.expensetracker.general.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginResponseDto extends ApiResponse {

    private String token;
    private String refreshToken;

    public LoginResponseDto(String message, Status status, String token, String refreshToken) {
        super(message, status);
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public LoginResponseDto(String message,Status status){
        super(message, status);
    }
}
