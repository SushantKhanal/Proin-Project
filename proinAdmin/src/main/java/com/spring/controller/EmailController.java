package com.spring.controller;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.mail.SimpleMailMessage;
        import org.springframework.mail.javamail.JavaMailSender;
        import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    // This Method Is Used To Prepare The Email Message And Send It To The Client
    @PostMapping("/adminAccount/sendEmail/")
    public ResponseEntity<Void> sendEmailToClient(@RequestBody String getMailTo) {

        String emailSubject = "About Proin app Sign Up";
        String emailBody = "Hey your Sign Up was Successful";
        String recipient = getMailTo;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipient);
        message.setSubject(emailSubject);
        message.setText(emailBody);
        message.setFrom("ProinProject@gmail.com");
        try{
            mailSender.send(message);
        } catch(Exception e) {
            System.out.println(e);
        }

        return new ResponseEntity<Void>(HttpStatus.OK);

    }


}