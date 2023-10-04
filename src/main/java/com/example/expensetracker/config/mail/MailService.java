package com.example.expensetracker.config.mail;

import com.example.expensetracker.data.dto.request.EmailNotificationRequest;
import com.example.expensetracker.general.ApiResponse;

public interface MailService {

    ApiResponse<?> sendMail(EmailNotificationRequest emailNotificationRequest);
}
