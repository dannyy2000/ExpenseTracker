package com.example.expensetracker.data.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequest {

    @NotNull(message = "field name cannot be null")
    @NotEmpty(message = "field name cannot be empty")
    private String firstName;
    @NotNull(message = "field name cannot be null")
    @NotEmpty(message = "field name cannot be empty")
    private String lastName;
    @NotNull(message = "field name cannot be null")
    @NotEmpty(message = "field name cannot be empty")
    private String email;
    @NotNull(message = "field name cannot be null")
    @NotEmpty(message = "field name cannot be empty")
    private String password;
}
