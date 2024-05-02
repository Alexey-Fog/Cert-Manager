package com.company.accountandcertificatemanager.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

@Service(OpenSSLService.NAME)
public class OpenSSLServiceBean implements OpenSSLService {

    @Override
    public void generateCertificate(String user, String mail, String org, String res, String passw) throws IOException, InterruptedException {
        Process proc1 = Runtime.getRuntime().exec(
                String.format("openssl req -new -newkey rsa:4096 -nodes " +
                        "-keyout %1$s.key " +
                        "-subj \"/C=RU/ST=SPb/O=%2$s/OU=%1$s/CN=%3$s/emailAddress=%4$s\" " +
                        "-out %1$s.csr", user, org, res, mail));
        Process proc2 = Runtime.getRuntime().exec(
                String.format("openssl ca -config ca.conf -in %1$s.csr " +
                        "-out %1$s.crt -batch", user));
        Process proc3 = Runtime.getRuntime().exec(
                String.format("openssl pkcs12 -export -in %1$s.crt -inkey %1$s.key -clcerts " +
                        "-out %1$s.p12 -passout pass:%2$s", user, passw));
        Process proc4 = Runtime.getRuntime().exec(
                String.format("openssl pkcs12 -in %1$s.p12 -out %1$s.pem -passin pass:%2$s -passout pass:%2$s", user, passw));
        // Ожидание завершения процесса
        proc1.waitFor();
        proc2.waitFor();
        proc3.waitFor();
        proc4.waitFor();
    }

    @Override
    public void sendCertificateByEmail(String user, String mail) {
        // Настройки SMTP сервера
        String host = "smtp.example.com";
        String port = "587";
        String username = "your_email@example.com";
        String password = "your_email_password";

        // Настройка свойств JavaMail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Получение сессии
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Создание сообщения
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
            message.setSubject("Сертификаты");

//            // Создание Multipart объекта
//            Multipart multipart = new MimeMultipart();
//
//            // Добавление частей сообщения
//            addAttachment(multipart, "certificate.pem");
//            addAttachment(multipart, "private_key.pem");
//
//            // Установка содержимого сообщения
//            message.setContent(multipart);

            // Отправка сообщения
            Transport.send(message);

//            System.out.println("Сертификаты успешно отправлены по электронной почте.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}