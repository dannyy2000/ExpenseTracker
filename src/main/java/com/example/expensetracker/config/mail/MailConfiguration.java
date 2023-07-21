package com.example.expensetracker.config.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MailConfiguration {

    private String apiKey;
    private String mailUrl;

}
