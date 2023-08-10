package com.example.expensetracker.exception;

public class UserNotFoundException extends CustomException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
