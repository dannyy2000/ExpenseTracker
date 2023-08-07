package com.example.expensetracker.data.dto.response;

import com.example.expensetracker.enums.Status;
import com.example.expensetracker.general.ApiResponse;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerifyOtpResponseDto extends ApiResponse {

    private String otp;

    public VerifyOtpResponseDto(String message, Status status, String otp) {
        super(message, status);
        this.otp = otp;
    }
}
