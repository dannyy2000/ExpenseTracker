package com.example.expensetracker.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifyOtpRequest {
    @NotEmpty(message = "Otp cannot be empty")
    @NotBlank(message = "Otp cannot be blank")
    private String otp;
}
