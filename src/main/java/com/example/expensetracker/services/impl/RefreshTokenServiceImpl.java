package com.example.expensetracker.services.impl;

import com.example.expensetracker.data.models.RefreshToken;
import com.example.expensetracker.data.repositories.RefreshTokenRepository;
import com.example.expensetracker.services.interfaces.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;


    @Override
    public RefreshToken generateRefreshToken(Long userId) {
        Instant now = Instant.now();
        Instant expiryTime = now.plus(1, ChronoUnit.DAYS);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setSupplierId(userId);
        refreshToken.setCreationTime(now);
        refreshToken.setExpirationTime(expiryTime);

       return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);

    }

    @Override
    public void deleteAllRefreshToken() {
        refreshTokenRepository.deleteAll();

    }
}

