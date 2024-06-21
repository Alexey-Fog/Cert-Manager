package com.company.accountandcertificatemanager.service;

import org.springframework.stereotype.Service;
import java.io.IOException;

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
                                  String outputFolder, String opensslPath, String caconfPath, Long duration)
            throws IOException, InterruptedException {
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
                "-batch", "-days", duration.toString()
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
        if (exitValue == 0) {
            exitValue = runProcess(opensslCommand2);
            if (exitValue == 0) {
                exitValue = runProcess(opensslCommand3);
                if (exitValue == 0) {
                    exitValue = runProcess(opensslCommand4);
                    if (exitValue == 0) {
                        return;
                    }
                }
            }
        }
    }

    @Override
    public Boolean revokeCertificate(String user, String certificateFileName, String outputFolder, String opensslPath,
                                     String caconfPath) throws IOException, InterruptedException {
        String[] opensslCommand = {
                opensslPath,
                "ca", "-config", caconfPath,
                "-revoke", outputFolder + "\\" + user + ".pem",
                "-batch"
        };

        int exitValue = runProcess(opensslCommand);
        return exitValue == 0;
    }

}