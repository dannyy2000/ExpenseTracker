package com.example.expensetracker.exception;

public class InvalidPasswordException extends CustomException{
    public InvalidPasswordException(String message) {
        super(message);
    }
}
