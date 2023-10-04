package com.example.expensetracker.services.impl;

import com.example.expensetracker.config.mail.MailService;
import com.example.expensetracker.data.dto.request.EmailNotificationRequest;

import com.example.expensetracker.data.dto.request.LoginRequest;
import com.example.expensetracker.data.dto.request.SignUpRequest;
import com.example.expensetracker.data.dto.request.VerifyUserRequest;
import com.example.expensetracker.data.dto.response.GenerateOtpResponse;
import com.example.expensetracker.data.dto.response.LoginResponseDto;
import com.example.expensetracker.data.models.AppUser;
import com.example.expensetracker.data.models.AuthOtp;
import com.example.expensetracker.data.repositories.AppUserRepository;
import com.example.expensetracker.data.repositories.AuthOtpRepository;
import com.example.expensetracker.enums.Role;
import com.example.expensetracker.enums.Status;
import com.example.expensetracker.exception.*;
import com.example.expensetracker.general.ApiResponse;
import com.example.expensetracker.security.JwtProvider;
import com.example.expensetracker.security.UserPrincipal;
import com.example.expensetracker.services.interfaces.AppUserService;
import com.example.expensetracker.services.interfaces.AuthOtpService;
import com.example.expensetracker.services.interfaces.AuthenticationService;
import com.example.expensetracker.services.interfaces.RefreshTokenService;
import com.example.expensetracker.utils.AppUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static com.example.expensetracker.general.Message.*;


@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AppUserService appUserService;
    private final AuthOtpService authOtpService;
    private final AppUserRepository appUserRepository;
    private final AuthOtpRepository authOtpRepository;
    private final AppUtils appUtils;

    private final MailService mailService;
    private final RefreshTokenService refreshTokenService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    @Override
    public ApiResponse<?> register(SignUpRequest signUpRequest) {
        try {
            Optional<AppUser> findUser = appUserRepository.findUserByEmail(signUpRequest.getEmail());

            if (findUser.isPresent()) throw new UserExistException(EMAIL_FOUND);

            AppUser appUser = AppUser.builder()
                    .firstName(signUpRequest.getFirstName())
                    .lastName(signUpRequest.getLastName())
                    .email(signUpRequest.getEmail())
                    .password(passwordEncoder.encode(signUpRequest.getPassword()))
                    .createdAt(new Date())
                    .role(Role.USER)
                    .build();
            appUserRepository.save(appUser);

            GenerateOtpResponse otpResponse = authOtpService.generateOtp();
            String otp = otpResponse.getOtp();

            EmailNotificationRequest emailNotificationRequest = new EmailNotificationRequest();
            emailNotificationRequest.setRecipients(signUpRequest.getEmail());
            emailNotificationRequest.setText("Your otp is " + otp);
            mailService.sendMail(emailNotificationRequest);

            return ApiResponse.builder()
                    .message("Thanks for signing up. Kindly check your email to activate your account")
                    .status(Status.SUCCESS)
                    .data(appUser)
                    .build();
        } catch (UserExistException e) {
            return ApiResponse.builder()
                    .message(e.getLocalizedMessage())
                    .status(Status.BAD_REQUEST)
                    .build();
        }
    }

    @Override
    public ApiResponse<?> login(LoginRequest loginRequest) {
        try {
            AppUser user = appUserService.findUserByEmail(loginRequest.getEmail());
            log.info("This is the user {}",user.getEmail());

            if (!user.isEnabled()) {
                throw new UserNotEnabledException("User is not enabled");
            }

            boolean isPassword = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
            if (!isPassword) {
                throw new InvalidPasswordException("Password does not match");
            }

            Authentication authentication = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("Security kinikankinikan {}",SecurityContextHolder.getContext().getAuthentication());

            UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

            String jwt = jwtProvider.generateToken(userDetails);

            return new LoginResponseDto(LOGIN_SUCCESS,
                    Status.SUCCESS,
                    jwt, refreshTokenService.generateRefreshToken(user.getId()).getToken());
        } catch (UserNotEnabledException | InvalidPasswordException | AuthenticationException e) {
            return new LoginResponseDto(e.getLocalizedMessage(), Status.BAD_REQUEST);
        }
    }

    @Override
    public ApiResponse<?> verifyUser(VerifyUserRequest verifyUserRequest) {
        try {

            Optional<AppUser> optionalAppUser = appUserRepository.findUserByEmail(verifyUserRequest.getEmail());

            if (!optionalAppUser.isPresent()) {
                throw new UserNotFoundException(USER_NOT_FOUND);
            }

            Optional<AuthOtp> otpOptional = authOtpRepository.findByOtpValue(verifyUserRequest.getOtp());

            if (!otpOptional.isPresent() || otpOptional.get().isExpired() || otpOptional.get().isUsed()) {
                throw new OtpValidationException("Invalid Otp or Otp has been used");
            }

            optionalAppUser.get().setEnabled(true);
            appUserRepository.save(optionalAppUser.get());

            otpOptional.get().setUsed(true);
            authOtpRepository.save(otpOptional.get());

            return ApiResponse.builder()
                    .message(EMAIL_VERIFIED)
                    .status(Status.SUCCESS)
                    .build();
        } catch (UserNotFoundException | OtpValidationException e) {
            return ApiResponse.builder()
                    .message("Something went wrong" + e.getLocalizedMessage())
                    .status(Status.BAD_REQUEST)
                    .build();
        }
    }
}
