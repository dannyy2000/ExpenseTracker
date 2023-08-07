package com.example.expensetracker.general;

import com.example.expensetracker.enums.Status;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private String message;
    private Status status;
    private int statusCode;


    public ApiResponse(String message, Status status){
        this.message = message;
        this.status = status;
    }

    public ApiResponse(Status status){
        this.status = status;
    }
}
