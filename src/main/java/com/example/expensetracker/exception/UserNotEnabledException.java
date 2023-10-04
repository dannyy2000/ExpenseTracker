package com.example.expensetracker.exception;

public class UserNotEnabledException extends CustomException{
    public UserNotEnabledException(String message) {
        super(message);
    }
}
