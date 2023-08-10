package com.example.expensetracker.controllers;

import com.example.expensetracker.data.dto.request.SignUpRequest;
import com.example.expensetracker.general.ApiResponse;
import com.example.expensetracker.services.interfaces.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController extends Controller {

    private  final AuthenticationService auth;

    public AuthController(HttpServletResponse response, AuthenticationService auth) {
        super(response);
        this.auth = auth;
    }


   @PostMapping("/signUp")
    public ApiResponse<?> signUp(@RequestBody @Valid SignUpRequest signUpRequest){
       return responseWithUpdatedHttpStatus(auth.register(signUpRequest));
   }

}
