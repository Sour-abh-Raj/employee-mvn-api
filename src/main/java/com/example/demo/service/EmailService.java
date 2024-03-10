package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    public void sendEmailToManager(String managerEmail, String employeeName, String phoneNumber, String emailId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(managerEmail);
        message.setSubject("New Employee Added");
        message.setText("Dear Manager,\n\n" +
                        "A new employee, " + employeeName + ", has been added to your team.\n" +
                        "Contact Details:\n" +
                        "Mobile: " + phoneNumber + "\n" +
                        "Email: " + emailId + "\n\n" +
                        "Best regards,\n" +
                        "EmployWise");
        emailSender.send(message);
    }
}