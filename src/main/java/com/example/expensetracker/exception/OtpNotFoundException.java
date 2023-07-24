package com.example.expensetracker.exception;

public class OtpNotFoundException extends RuntimeException{

    public OtpNotFoundException(String message) {
        super(message);
    }
}
