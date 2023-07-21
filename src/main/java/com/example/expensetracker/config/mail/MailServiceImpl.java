package com.example.expensetracker.config.mail;

import com.example.expensetracker.data.dto.request.EmailNotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService{

   private final MailConfiguration mailConfiguration;

    @Override
    public String sendMail(EmailNotificationRequest emailNotificationRequest) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("api-key",mailConfiguration.getApiKey());

        HttpEntity<EmailNotificationRequest> httpEntity =
                new HttpEntity<>(emailNotificationRequest,httpHeaders);

        ResponseEntity<String> response =
                restTemplate.postForEntity(mailConfiguration.getMailUrl(),httpEntity, String.class);

        return response.getBody();

    }
}
