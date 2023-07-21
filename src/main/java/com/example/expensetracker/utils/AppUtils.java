package com.example.expensetracker.utils;

import com.example.expensetracker.data.dto.request.EmailNotificationRequest;
import com.example.expensetracker.data.dto.request.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.security.SecureRandom;
import java.util.Map;

@Component
public class AppUtils {

    @Autowired
    private TemplateEngine templateEngine;


    public static String generateToken(int length){
        SecureRandom secureRandom = new SecureRandom();

        int max = (int)(Math.pow(10,length -1));
        return String.valueOf(secureRandom.nextInt(max));
    }

    public EmailNotificationRequest buildMail(String email, String token){
        EmailNotificationRequest request = new EmailNotificationRequest();

        request.getRecipients().add(new Recipient(email));

        Context context = new Context();
        context.setVariables(Map.of("email",email,"token",token));

        String content = templateEngine.process("Activate",context);
        request.setHtmlContent(content);
        return request;
    }
}
