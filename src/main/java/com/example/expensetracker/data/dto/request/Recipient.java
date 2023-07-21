package com.example.expensetracker.data.dto.request;

import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipient {
    @Email
    private String email;
}
