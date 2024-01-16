package com.example.expensetracker.controllers;

import com.example.expensetracker.data.dto.request.LoginRequest;
import com.example.expensetracker.data.dto.request.SignUpRequest;
import com.example.expensetracker.data.dto.request.VerifyUserRequest;
import com.example.expensetracker.general.ApiResponse;
import com.example.expensetracker.services.interfaces.AuthenticationService;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController extends Controller {

    private  final AuthenticationService auth;

    public AuthController(HttpServletResponse response, AuthenticationService auth) {
        super(response);
        this.auth = auth;
    }


   @PostMapping("/signUp")
   @PermitAll
    public ApiResponse<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest){
       return responseWithUpdatedHttpStatus(auth.register(signUpRequest));
   }

   @PostMapping("/signIn")
   @PermitAll
   public ApiResponse<?>signIn(@Valid @RequestBody LoginRequest loginRequest){
        return responseWithUpdatedHttpStatus(auth.login(loginRequest));
   }

   @PostMapping("/verify")
   @PermitAll
    public ApiResponse<?>verify(@Valid @RequestBody VerifyUserRequest verifyUserRequest){
        return responseWithUpdatedHttpStatus(auth.verifyUser(verifyUserRequest));
   }

}
