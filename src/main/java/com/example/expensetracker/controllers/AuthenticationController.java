package com.example.expensetracker.controllers;

import com.example.expensetracker.data.dto.request.SignUpRequest;
import com.example.expensetracker.exception.EmailFoundException;
import com.example.expensetracker.general.ApiResponse;
import com.example.expensetracker.services.interfaces.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/request")
    public ResponseEntity<?> requestSignUp(@RequestParam String email) throws EmailFoundException {
        ApiResponse response = authenticationService.requestSignUp(email);
        return ResponseEntity.ok(response);
    }

   @PostMapping("/confirm")
    public ResponseEntity<?> confirmOtp(@RequestParam String email,String token){
        var response = authenticationService.confirmOtp(email,token);
        return ResponseEntity.ok(response);
   }

   @PostMapping("/confirmSignUp")
    public ResponseEntity<?> complete(@RequestBody SignUpRequest signUpRequest){
        ApiResponse response = authenticationService.completeSignUp(signUpRequest);
        return ResponseEntity.ok(response);
   }

}
