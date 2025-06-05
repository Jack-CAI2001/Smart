package com.smart.smartApi.service;

import com.smart.smartApi.dto.MailBodyDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String email;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendSimpleMessage(MailBodyDto mailBodyDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailBodyDto.to());
        message.setFrom(email);
        message.setSubject(mailBodyDto.subject());
        message.setText(mailBodyDto.text());

        javaMailSender.send(message);
    }
}
