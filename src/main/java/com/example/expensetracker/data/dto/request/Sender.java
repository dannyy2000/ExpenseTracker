package com.example.expensetracker.data.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sender {
    @NotEmpty(message = "This field cannot be empty")
    @NotNull(message = "This field cannot be null")
    private String name;
    @Email
    private String email;
}
