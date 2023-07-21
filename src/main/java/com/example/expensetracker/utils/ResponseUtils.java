package com.example.expensetracker.utils;

import com.example.expensetracker.enums.ResponseMessage;
import com.example.expensetracker.general.ApiResponse;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtils {

    public static ApiResponse getSuccessResponse(){
       return ApiResponse.builder()
                .success(true)
                .message(ResponseMessage.SUCCESS)
                .statusCode(200)
                .build();
    }

    public static ApiResponse getFailedResponse(){
        return ApiResponse.builder()
                .success(false)
                .message(ResponseMessage.UNAUTHORIZED)
                .statusCode(401)
                .build();
    }

    public static ApiResponse getConfirmedResponse(){
        return ApiResponse.builder()
                .success(true)
                .message(ResponseMessage.CONFIRMED)
                .statusCode(200)
                .build();

    }

    public static ApiResponse getCreatedResponse(){
        return ApiResponse.builder()
                .success(true)
                .message(ResponseMessage.CREATED)
                .statusCode(200)
                .build();

    }


}
