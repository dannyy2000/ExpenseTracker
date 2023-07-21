//package com.example.expensetracker.services.impl;
//
//import com.example.expensetracker.data.dto.request.RegisterRequest;
//import com.example.expensetracker.data.dto.response.RegisterResponse;
//import com.example.expensetracker.data.models.AppUser;
//import com.example.expensetracker.data.repositories.AppUserRepository;
//import com.example.expensetracker.exception.UserExistException;
//import com.example.expensetracker.services.interfaces.AppUserService;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@Slf4j
//@AllArgsConstructor
//public class AppUserServiceImpl implements AppUserService {
//
//    private final AppUserRepository appUserRepository;
//
//    @Override
//    public RegisterResponse registerUser(RegisterRequest registerRequest) {
//        log.info("registering user with payload {}",registerRequest);
//
//        Optional<AppUser> userFound = appUserRepository.findUserByEmail(registerRequest.getEmail());
//        if(userFound.isPresent()) throw new UserExistException("User already exists");
//        AppUser appUser = AppUser.builder()
//                .firstName(registerRequest.getFirstName())
//                .lastName(registerRequest.getLastName())
//                .email(registerRequest.getEmail())
//                .password(registerRequest.getPassword())
//                .build();
//        appUserRepository.save(appUser);
//
//        return getUserRegisterResponse(appUser);
//    }
//
//    private RegisterResponse getUserRegisterResponse(AppUser appUser) {
//        RegisterResponse registerResponse = new RegisterResponse();
//        registerResponse.setId(appUser.getId());
//        registerResponse.setMessage("Registration sucessfully");
//        return registerResponse;
//    }
//}
