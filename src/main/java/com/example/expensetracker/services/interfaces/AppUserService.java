package com.example.expensetracker.services.interfaces;

import com.example.expensetracker.data.models.AppUser;
import com.example.expensetracker.security.UserPrincipal;

import java.util.Optional;

public interface AppUserService {

    void saveUser(AppUser appUser);

    AppUser findUserByEmail(String email);

    Optional<AppUser> getCurrentUser();

    Optional<AppUser> currentUser();


}
