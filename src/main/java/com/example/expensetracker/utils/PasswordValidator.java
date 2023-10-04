package com.example.expensetracker.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

    private static final String PASSWORD_PATTERN = "^(?=.*[a-zA-Z0-9])(?=.*[@$!%*#?&])[A-Za-z0-9@$!%*#?&]{8,}$";


    public static boolean validatePassword(String firstName, String lastname,String password){
        if(password.length() < 8){
            return false;
        }

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        if(!matcher.matches()){
            return false;
        }

        if(password.equalsIgnoreCase(firstName) || password.equalsIgnoreCase(lastname)){
            return false;
        }

        return true;

    }


}
