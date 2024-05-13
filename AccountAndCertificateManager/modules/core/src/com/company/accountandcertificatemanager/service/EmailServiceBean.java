package com.company.accountandcertificatemanager.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

@Service(EmailService.NAME)
public class EmailServiceBean implements EmailService {

    @Override
    public void sendCertificatesByEmail(String userEmail, String[] certificateFiles) {
        String from = "your-email@example.com"; // Замените на ваш адрес электронной почты
        String password = "your-email-password"; // Замените на пароль от вашей электронной почты
        String to = userEmail;

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.example.com"); // Замените на адрес SMTP-сервера вашей почты
        properties.put("mail.smtp.port", "587"); // Замените на порт SMTP-сервера вашей почты
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Ваши сертификаты");

            Multipart multipart = new MimeMultipart();

            for (String certificateFile : certificateFiles) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.attachFile(certificateFile);
                multipart.addBodyPart(attachmentPart);
            }

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Сертификаты успешно отправлены по электронной почте.");
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при отправке сертификатов по электронной почте: " + e.getMessage());
        }
    }
}