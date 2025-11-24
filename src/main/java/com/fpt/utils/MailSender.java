package com.fpt.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;

import static com.fpt.utils.ConfigReader.getProperty;

public class MailSender {
    private final String from = getProperty("mail.username");
    private final String password = getProperty("mail.password");
    private String filePath = System.getProperty("user.dir")+"/report/emailable-report.html";
    private final Logger logger = LoggerFactory.getLogger(MailSender.class);

    public void sendMail(String to, String subject, String text) {
        logger.info("Sending mail to "+to);
        Properties props = new Properties();
        props.put("mail.smtp.host", getProperty("mail.host"));
        props.put("mail.smtp.port", getProperty("mail.port"));
        props.put("mail.smtp.auth", getProperty("mail.properties.mail.smtp.auth"));
        props.put("mail.smtp.ssl.protocols", getProperty("mail.protocol"));
        props.put("mail.smtp.starttls.enable", getProperty("mail.properties.mail.smtp.starttls.enable"));

        // create session & login with email/pass
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setSentDate(new Date());

            // Read file content as HTML
            File reportFile = new File(filePath);
            if (!reportFile.exists()) {
                throw new RuntimeException("Attachment file not found: " + filePath);
            }
            // some des above report
            String description = "<p>"+text+"</p><br>";
            String htmlContent = new String(Files.readAllBytes(Paths.get(filePath)));

            // Set HTML content as email body
            msg.setContent(description+htmlContent, "text/html");

            Transport.send(msg);
            logger.info("Send mail successfully");
        } catch (MessagingException | IOException mex) {
            System.out.println("send failed, exception: " + mex);
        }
    }
}