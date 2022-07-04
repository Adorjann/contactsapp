package com.adorjanpraksa.contactsapp.service.dao;

import com.adorjanpraksa.contactsapp.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NotificationDao {

    private final EmailService emailService;

    public void sendEmail(String firstName, String email) {

        emailService.sendEmail(firstName, email);
    }
}
