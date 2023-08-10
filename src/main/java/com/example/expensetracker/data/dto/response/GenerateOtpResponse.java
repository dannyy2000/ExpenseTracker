package com.example.expensetracker.data.dto.response;

import com.example.expensetracker.enums.Status;
import com.example.expensetracker.general.ApiResponse;
import com.example.expensetracker.general.Message;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenerateOtpResponse extends ApiResponse {

    private Long otpId;
    private String otp;

    public GenerateOtpResponse(String message, Status status, Long otpId, String otp) {
        super(message, status);
        this.otpId = otpId;
        this.otp = otp;
    }
}
