package com.example.expensetracker.services.interfaces;

import com.example.expensetracker.data.models.AppUser;

public interface AppUserService {

    void saveUser(AppUser appUser);

    AppUser findUserByEmail(String email);


}
