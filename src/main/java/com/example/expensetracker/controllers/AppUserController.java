package com.example.expensetracker.controllers;

import com.example.expensetracker.data.dto.request.RegisterRequest;
import com.example.expensetracker.data.dto.response.RegisterResponse;
import com.example.expensetracker.services.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

   @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){

        RegisterResponse registerResponse = appUserService.registerUser(registerRequest);
        return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
    }


}
