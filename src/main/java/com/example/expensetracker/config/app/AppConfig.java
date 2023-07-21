package com.example.expensetracker.config.app;

import com.example.expensetracker.config.mail.MailConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("{mail.api.key}")
    private String mailApiKey;
    @Value("{sendinblue.mail.url}")
    private String mailUrl;


    @Bean
    public MailConfiguration mailConfiguration(){
        return new MailConfiguration(mailApiKey,mailUrl);
    }
}
