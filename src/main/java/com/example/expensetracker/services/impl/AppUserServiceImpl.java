package com.example.expensetracker.services.impl;
import com.example.expensetracker.data.models.AppUser;
import com.example.expensetracker.data.repositories.AppUserRepository;
import com.example.expensetracker.exception.UserNotFoundException;
import com.example.expensetracker.services.interfaces.AppUserService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.expensetracker.general.Message.EMAIL_NOT_FOUND;

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
    public AppUser findUserByEmail(@NotBlank @NotEmpty String email) {
        Optional<AppUser> foundUserEmail = appUserRepository.findUserByEmail(email);

        if(foundUserEmail.isEmpty()){
            throw new UserNotFoundException(EMAIL_NOT_FOUND);
        }

        return foundUserEmail.get();

    }

    @Override
    public Optional<AppUser> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if(authentication == null || !authentication.isAuthenticated()){
            log.info("Authentication is null or not authenticated.");
            return Optional.empty();
        }
        Object principal = authentication.getPrincipal();

        if(principal instanceof UserDetails userDetails){
            log.info("User is authenticated as: {}", userDetails.getUsername());
            return appUserRepository.findUserByEmail(userDetails.getUsername());

        }
        log.info("Principal is not an instance of UserDetails.");
        return Optional.empty();
    }


}
