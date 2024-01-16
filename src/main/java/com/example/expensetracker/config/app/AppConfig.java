package com.example.expensetracker.config.app;

import com.example.expensetracker.config.mail.MailConfiguration;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Key;

@Configuration
public class AppConfig {

    @Value("${spring.mail.host}")
    private String mailHost;
    @Value("${spring.mail.port}")
    private String mailPort;
    @Value("${spring.mail.username}")
    private String mailUsername;
    @Value("${spring.mail.password}")
    private String mailPassword;

//    @Value("${jwt.secret}")
//    private String jwtSecret;
//
//    @Bean
//    public Key getSigningKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }



    @Bean
    public MailConfiguration mailConfiguration(){
        return new MailConfiguration(mailHost,mailPort,mailUsername,mailPassword);
    }
}
