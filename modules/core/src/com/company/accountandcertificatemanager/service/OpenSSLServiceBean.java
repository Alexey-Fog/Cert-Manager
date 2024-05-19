package com.company.accountandcertificatemanager.service;

import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Service(OpenSSLService.NAME)
public class OpenSSLServiceBean implements OpenSSLService {
    private int runProcess(String[] command) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        Process process = pb.start();
        process.waitFor();
        return process.exitValue();
    }

    @Override
    public void createCertificate(String user, String mail, String org, String res, String passw,
                                  String outputFolder, String opensslPath, String caconfPath) throws IOException, InterruptedException {
// Создание .key и .csr
        String[] opensslCommand1 = {
                opensslPath,
                "req", "-new",
                "-newkey", "rsa:4096", "-nodes",
                "-keyout", outputFolder + "\\" + user + ".key",
                "-subj", "/C=RU/ST=SPb/O=" + org + "/OU=" + user + "/CN=" + res + "/emailAddress=" + mail,
                "-out", outputFolder + "\\" + user + ".csr"
        };

        // Создание .crt
        String[] opensslCommand2 = {
                opensslPath,
                "ca", "-config", caconfPath,
                "-in", outputFolder + "\\" + user + ".csr",
                "-out", outputFolder + "\\" + user + ".crt",
                "-batch"
        };

        // Создание .p12
        String[] opensslCommand3 = {
                opensslPath,
                "pkcs12", "-export", "-in", outputFolder + "\\" + user + ".crt",
                "-inkey", outputFolder + "\\" + user + ".key",
                "-clcerts", "-out", outputFolder + "\\" + user + ".p12",
                "-passout", "pass:" + passw
        };

        // Создание .pem
        String[] opensslCommand4 = {
                opensslPath,
                "pkcs12", "-in", outputFolder + "\\" + user + ".p12",
                "-out", outputFolder + "\\" + user + ".pem",
                "-passin", "pass:" + passw,
                "-passout", "pass:" + passw
        };

        int exitValue = runProcess(opensslCommand1);
//      if (exitValue == 0) {
//            exitValue = runProcess(opensslCommand2);
//
//            if (exitValue == 0) {
//                exitValue = runProcess(opensslCommand3);
////
////            if (exitValue == 0) {
//                    exitValue = runProcess(opensslCommand4);
////
////            if (exitValue == 0) {
//                        return;
//                    }
//                }
//            }
//        }

    }

    @Override
    public Boolean revokeCertificate(String certificateFileName, String outputFolder, String opensslPath,
                                     String caconfPath) throws IOException, InterruptedException {
        String[] opensslCommand = {
                opensslPath,
                "ca", "-config", caconfPath,
                "-revoke", outputFolder + "\\" + certificateFileName,
                "-batch"
        };

        int exitValue = runProcess(opensslCommand);

        return exitValue == 0;
    }

    @Override
    public void sendEmail(String userEmail) throws MessagingException {
        String from = "email@example.com";
        String to = userEmail;

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.freesmtpservers.com");
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properties);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Ваши сертификаты");

        Multipart multipart = new MimeMultipart();

        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("Hello, my friend! Here are your files:");
        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = new MimeBodyPart();
        FileDataSource source = new FileDataSource("C:\\Users\\alpet\\Downloads\\newEmpty.zip");
        messageBodyPart.setDataHandler(new DataHandler(source));
        multipart.addBodyPart(messageBodyPart);


//            for (String certificateFile : certificateFiles) {
//                MimeBodyPart attachmentPart = new MimeBodyPart();
//                attachmentPart.attachFile(certificateFile);
//                multipart.addBodyPart(attachmentPart);
//            }

        message.setContent(multipart);

        Transport.send(message);
        Boolean t = true;
    }
}