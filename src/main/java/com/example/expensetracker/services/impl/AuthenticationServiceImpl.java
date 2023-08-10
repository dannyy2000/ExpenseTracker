package com.example.expensetracker.services.impl;

import com.example.expensetracker.config.mail.MailService;
import com.example.expensetracker.data.dto.request.EmailNotificationRequest;

import com.example.expensetracker.data.dto.request.SignUpRequest;
import com.example.expensetracker.data.dto.response.GenerateOtpResponse;
import com.example.expensetracker.data.models.AppUser;
import com.example.expensetracker.data.repositories.AppUserRepository;
import com.example.expensetracker.enums.Status;
import com.example.expensetracker.exception.UserExistException;
import com.example.expensetracker.general.ApiResponse;
import com.example.expensetracker.services.interfaces.AppUserService;
import com.example.expensetracker.services.interfaces.AuthOtpService;
import com.example.expensetracker.services.interfaces.AuthenticationService;
import com.example.expensetracker.utils.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static com.example.expensetracker.general.Message.EMAIL_FOUND;


@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AppUserService appUserService;
    private final AuthOtpService authOtpService;
    private final AppUserRepository appUserRepository;
    private final AppUtils appUtils;

    private final MailService mailService;


    @Override
    public ApiResponse<?> register(SignUpRequest signUpRequest) {
        try {
            Optional<AppUser> findUser = appUserRepository.findUserByEmail(signUpRequest.getEmail());

            if (findUser.isPresent()) throw new UserExistException(EMAIL_FOUND);

            AppUser appUser = AppUser.builder()
                    .firstName(signUpRequest.getFirstName())
                    .lastName(signUpRequest.getLastName())
                    .email(signUpRequest.getEmail())
                    .password(signUpRequest.getPassword())
                    .createdAt(new Date())
                    .build();
            appUserService.saveUser(appUser);

            GenerateOtpResponse otpResponse = authOtpService.generateOtp();
            String otp = otpResponse.getOtp();

            EmailNotificationRequest emailNotificationRequest = new EmailNotificationRequest();
            emailNotificationRequest.setRecipients(signUpRequest.getEmail());
            emailNotificationRequest.setHtmlContent("Your otp is " + otp);
            mailService.sendMail(emailNotificationRequest);

            return ApiResponse.builder()
                    .message("Thanks for signing up. Kindly check your email to activate your account")
                    .status(Status.SUCCESS)
                    .data(appUser)
                    .build();
        }catch (UserExistException e){
             return ApiResponse.builder()
                     .message(e.getLocalizedMessage())
                     .status(Status.BAD_REQUEST)
                     .build();
        }
    }
}
