package com.example.expensetracker.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthOtp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String otpValue;
    private LocalDateTime creationTime = LocalDateTime.now();
    private LocalDateTime expiryTime = creationTime.plusMinutes (20);
    private String recipient;
    private LocalDateTime confirmedAt;

}
