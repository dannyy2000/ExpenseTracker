//package com.example.expensetracker.services.impl;
//
//import com.example.expensetracker.config.mail.MailService;
//import com.example.expensetracker.data.dto.request.EmailNotificationRequest;
//import com.example.expensetracker.data.dto.request.LoginRequest;
//import com.example.expensetracker.data.dto.request.SignUpRequest;
//import com.example.expensetracker.data.dto.response.TokenResponse;
//import com.example.expensetracker.data.models.AppUser;
//import com.example.expensetracker.data.models.AuthOtp;
//import com.example.expensetracker.exception.EmailFoundException;
//import com.example.expensetracker.exception.OtpNotFoundException;
//import com.example.expensetracker.exception.OtpValidationException;
//import com.example.expensetracker.general.ApiResponse;
//import com.example.expensetracker.services.interfaces.AppUserService;
//import com.example.expensetracker.services.interfaces.AuthOtpService;
//import com.example.expensetracker.services.interfaces.AuthenticationService;
//import com.example.expensetracker.utils.AppUtils;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//import static com.example.expensetracker.general.Message.*;
//import static com.example.expensetracker.utils.AppUtils.generateToken;
//import static com.example.expensetracker.utils.ResponseUtils.*;
//
//@Service
//@AllArgsConstructor
//public class AuthenticationServiceImpl implements AuthenticationService {
//
//    private final AppUserService appUserService;
//    private final AuthOtpService authOtpService;
//    private final AppUtils appUtils;
//
//    private final MailService mailService;
//    @Override
//    public ApiResponse requestSignUp(String email) throws EmailFoundException {
//        AppUser checkEmail = appUserService.findUserByEmail(email);
//
//        if(checkEmail != null) throw new EmailFoundException(EMAIL_FOUND);
//
//        String token = generateToken(4);
//
//        AuthOtp authOtp = new AuthOtp(token,
//                LocalDateTime.now(),LocalDateTime.now().plusMinutes(20),
//                email);
//
//        authOtpService.saveOtp(authOtp);
//
//        EmailNotificationRequest emailNotificationRequest = appUtils.buildMail(
//                email,authOtp.getOtpValue());
//
//        String response = mailService.sendMail(emailNotificationRequest);
//
//        if(response == null) return getFailedResponse();
//
//        return getCreatedResponse();
//
//    }
//
//    @Override
//    public ApiResponse confirmOtp(String email, String otp) {
//        AuthOtp verifyToken = authOtpService.checkOtp(otp).orElseThrow(()-> new OtpNotFoundException(AUTH_OTP_NOT_FOUND));
//
//        LocalDateTime expiryTime = verifyToken.getExpiryTime();
//        if(expiryTime.isBefore(LocalDateTime.now()))throw new OtpValidationException(AUTH_OTP_EXPIRED);
//
//        authOtpService.setConfirmedAt(otp);
//
//        return getConfirmedResponse();
//    }
//
//    @Override
//    public ApiResponse completeSignUp(SignUpRequest signUpRequest) {
//        AppUser appUser = AppUser.builder()
//                .firstName(signUpRequest.getFirstName())
//                .lastName(signUpRequest.getLastName())
//                .email(signUpRequest.getEmail())
//                .password(signUpRequest.getPassword())
//                .build();
//        appUserService.saveUser(appUser);
//
//        return getCreatedResponse();
//
//    }
//
//    @Override
//    public TokenResponse login(LoginRequest loginRequest) {
//        return null;
//    }
//}
