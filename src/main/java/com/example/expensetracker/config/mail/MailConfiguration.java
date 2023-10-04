package com.example.expensetracker.config.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MailConfiguration {

    private String mailHost;
    private String mailPort;
    private String mailUsername;
    private String mailPassword;

}
