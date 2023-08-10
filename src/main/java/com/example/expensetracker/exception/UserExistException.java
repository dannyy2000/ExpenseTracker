package com.example.expensetracker.exception;

public class UserExistException extends CustomException{

    public UserExistException(String message) {
        super(message);
    }
}
