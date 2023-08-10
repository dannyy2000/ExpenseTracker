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
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "token_id")

    private Long id;
    private String otpValue;
    private LocalDateTime creationTime = LocalDateTime.now();
    private LocalDateTime expiryTime;
    private boolean used;
    public boolean isExpired(){
        return creationTime.isAfter(expiryTime);
    }
}
