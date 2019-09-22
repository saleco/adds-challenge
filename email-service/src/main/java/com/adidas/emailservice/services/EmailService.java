package com.adidas.emailservice.services;

import com.adidas.emailservice.model.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendNotificaitoin(EmailDetails emailDetails) throws MailException, InterruptedException {

        System.out.println("Sending email...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(emailDetails.getEmailTo());
        mail.setFrom(emailDetails.getFrom());
        mail.setSubject(emailDetails.getEmailSubject());
        mail.setText(emailDetails.getEmailBody());
        javaMailSender.send(mail);

        System.out.println("Email Sent!");
    }
}
