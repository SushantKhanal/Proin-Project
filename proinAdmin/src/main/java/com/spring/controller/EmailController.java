package com.spring.controller;

        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.mail.SimpleMailMessage;
        import org.springframework.mail.javamail.JavaMailSender;
        import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {

    private JavaMailSender emailSender;

    public EmailController(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public EmailController() {
        super();
    }

    // This Method Is Used To Prepare The Email Message And Send It To The Client
    @PostMapping("/adminAccount/sendEmail/")
    public ResponseEntity<Void> sendEmailToClient(@RequestBody String getMailTo) {

        String emailSubject = "About Proin app Sign Up";
        String emailBody = "Hey your Sign Up was Succesful";
        String recipient = getMailTo;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipient);
        message.setSubject(emailSubject);
        message.setText(emailBody);
        emailSender.send(message);

        System.out.println("\nMessage Send Successfully.... Hurrey!\n");

        return new ResponseEntity<Void>(HttpStatus.OK);

    }


}