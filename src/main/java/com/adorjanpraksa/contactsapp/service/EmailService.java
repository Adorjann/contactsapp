package com.adorjanpraksa.contactsapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${notification.new-contact.from}")
    private String sender;

    public void sendEmail(String recipientName, String recipientEmail) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(sender);
        mailMessage.setTo(recipientEmail);
        mailMessage.setSubject("Welcome to Contact Manager");
        mailMessage.setText("Hi " + recipientName + " Thank you for choosing Contact Manager! Enjoy the experience..");

        javaMailSender.send(mailMessage);
    }
}
