package com.teamone.approvalserver.Services.Email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SmtpAuthenticator extends Authenticator {
    public SmtpAuthenticator(){
        super();
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {

        String username = "v1approvalnotification@gmail.com";
        String password = "ognianobzrxqqyqs";
        if ((username != null) && (username.length() > 0) && (password != null)
                && (password.length   () > 0)) {

            return new PasswordAuthentication(username, password);
        }

        return null;
    }

}
