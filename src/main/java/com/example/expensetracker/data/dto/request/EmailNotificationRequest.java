package com.example.expensetracker.data.dto.request;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailNotificationRequest {

    private final Sender sender = new Sender("Tracker","noreply@Tracker.co.uk");
    private String recipients;
    private final String subject = "welcome to Tracker";
    private String htmlContent;

}
