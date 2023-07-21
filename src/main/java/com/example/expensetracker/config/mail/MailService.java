package com.example.expensetracker.config.mail;

import com.example.expensetracker.data.dto.request.EmailNotificationRequest;

public interface MailService {

    String sendMail(EmailNotificationRequest emailNotificationRequest);
}
