package com.company.accountandcertificatemanager.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service(OpenSSLService.NAME)
public class OpenSSLServiceBean implements OpenSSLService {

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

        ProcessBuilder pb = new ProcessBuilder(opensslCommand1);
        pb.redirectErrorStream(true);
        Process process = pb.start();
        process.waitFor();

//        if (process.exitValue() == 0) {
//            ProcessBuilder pb2 = new ProcessBuilder(opensslCommand2);
//            pb2.redirectErrorStream(true);
//            Process process2 = pb2.start();
//            process2.waitFor();
//
//            if (process2.exitValue() == 0) {
//                pb = new ProcessBuilder(opensslCommand3);
//                pb.redirectErrorStream(true);
//                process = pb.start();
//                process.waitFor();
//
//                if (process.exitValue() == 0) {
//                    pb = new ProcessBuilder(opensslCommand4);
//                    pb.redirectErrorStream(true);
//                    process = pb.start();
//                    process.waitFor();
//
//                    if (process.exitValue() == 0) {
//                        return;
//                    }
//                }
//            }
//        }
    }

    @Override
    public void revokeCertificate(String certificateFileName, String outputFolder, String opensslPath,
                                  String caconfPath) throws IOException, InterruptedException {
        String[] opensslCommand = {
                opensslPath,
                "ca", "-config", caconfPath,
                "-revoke", outputFolder + "\\" + certificateFileName,
                "-batch"
        };

        ProcessBuilder pb = new ProcessBuilder(opensslCommand);
        pb.redirectErrorStream(true);
        Process process = pb.start();
        process.waitFor();

        if (process.exitValue() == 0) {
            System.out.println("Сертификат успешно отозван.");
        } else {
            System.out.println("Ошибка при отзыве сертификата. Код завершения: " + process.exitValue());
        }
    }
}