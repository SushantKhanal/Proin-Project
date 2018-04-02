package com.spring.scheduler;

import com.spring.requestDTO.EmailContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author : Suraj Gautam
 *         <suraj.gautam@f1soft.com>
 */
@Component
public class EmailSenderScheduler {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailToClient(EmailContent email) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getRecipient());
        message.setSubject(email.getEmailSubject());
        message.setText(email.getEmailBody());
        message.setFrom("ProinProject@gmail.com");
        try{
            mailSender.send(message);
        } catch(Exception e) {
            System.out.println(e);
        }

    }
}
