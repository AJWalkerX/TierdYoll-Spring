package com.ajwalker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    private final JavaMailSender mailSender;

    public void sendVerificationEmail(String recipientEmail, String link) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Lütfen hesabınızı doğrulayın");
        message.setText("Hesabınızı doğrulamak için lütfen aşağıdaki linke tıklayın:\n" + link);
        mailSender.send(message);
    }
}
