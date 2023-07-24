package com.example.expensetracker.services.impl;
import com.example.expensetracker.data.models.AppUser;
import com.example.expensetracker.data.repositories.AppUserRepository;
import com.example.expensetracker.services.interfaces.AppUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;


    @Override
    public void saveUser(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    @Override
    public AppUser findUserByEmail(String email) {
        return appUserRepository.findUserByEmail(email);
    }
}
