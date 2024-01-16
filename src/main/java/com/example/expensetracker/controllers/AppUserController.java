package com.example.expensetracker.controllers;

import com.example.expensetracker.data.models.AppUser;
import com.example.expensetracker.general.ApiResponse;
import com.example.expensetracker.services.interfaces.AppUserService;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AppUserController  {
    private final AppUserService appUserService;



    @GetMapping("/find")
    @PermitAll
    public ResponseEntity<AppUser> findUser(@RequestParam @Valid String email){
        return ResponseEntity.ok(appUserService.findUserByEmail(email));
    }
}
