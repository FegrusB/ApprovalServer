package com.teamone.approvalserver.Services;

import com.teamone.approvalserver.Models.EmailDetails;
import org.springframework.stereotype.Service;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


@Service
public class EmailService{
    public void sendEmail(EmailDetails emailDetails) {

        // Get system properties
        Properties props = new Properties();
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.host", emailDetails.getHost());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", true);
        Session session = Session.getDefaultInstance(props);


        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(emailDetails.getFrom()));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDetails.getTo()));

            // Set Subject: header field
            message.setSubject(emailDetails.getSubject());

            // Send the actual HTML message, as big as you like
            message.setContent(emailDetails.getContent(), "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }


}

