package com.example.expensetracker.utils;

import com.example.expensetracker.enums.Status;
import com.example.expensetracker.general.ApiResponse;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtils {

    public static ApiResponse getSuccessResponse(){
       return ApiResponse.builder()
                .success(true)
                .message(Status.SUCCESS)
                .statusCode(200)
                .build();
    }

    public static ApiResponse getFailedResponse(){
        return ApiResponse.builder()
                .success(false)
                .message(Status.UNAUTHORIZED)
                .statusCode(401)
                .build();
    }

    public static ApiResponse getConfirmedResponse(){
        return ApiResponse.builder()
                .success(true)
                .message(Status.CONFIRMED)
                .statusCode(200)
                .build();

    }

    public static ApiResponse getCreatedResponse(){
        return ApiResponse.builder()
                .success(true)
                .message(Status.CREATED)
                .statusCode(200)
                .build();

    }


}
