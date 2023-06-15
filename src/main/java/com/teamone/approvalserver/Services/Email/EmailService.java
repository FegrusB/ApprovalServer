package com.teamone.approvalserver.Services.Email;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


@Service
public class EmailService{
    public void sendEmail(EmailDetails emailDetails) {

        Properties props = new Properties();
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.host", emailDetails.getHost());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", true);
        SmtpAuthenticator authenticator = new SmtpAuthenticator();
        authenticator.getPasswordAuthentication();

        Session session = Session.getDefaultInstance(props,authenticator);


        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(emailDetails.getFrom()));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDetails.getTo()));

            // Set Subject: header field
            message.setSubject(emailDetails.getSubject());


            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(emailDetails.getContent(),"text/html; charset=utf-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            if(emailDetails.attachment != null){
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                attachmentBodyPart.attachFile(emailDetails.getAttachment());
                multipart.addBodyPart(attachmentBodyPart);
            }

            // Send the actual HTML message, as big as you like
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

