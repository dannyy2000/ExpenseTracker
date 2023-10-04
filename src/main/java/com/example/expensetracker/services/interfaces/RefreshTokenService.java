package com.example.expensetracker.services.interfaces;

import com.example.expensetracker.data.models.RefreshToken;

public interface RefreshTokenService {

     RefreshToken generateRefreshToken(Long userId);

     void deleteRefreshToken(String token);

     void deleteAllRefreshToken();

}
