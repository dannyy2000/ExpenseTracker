package com.example.expensetracker.general;

import com.example.expensetracker.enums.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ApiResponse {

    private boolean success;
    private ResponseMessage message;
    private int statusCode;
}
