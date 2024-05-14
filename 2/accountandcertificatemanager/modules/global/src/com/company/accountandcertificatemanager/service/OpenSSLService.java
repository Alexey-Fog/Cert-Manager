package com.company.accountandcertificatemanager.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

public interface OpenSSLService {
    String NAME = "accountandcertificatemanager_OpenSSLService";

    void createCertificate(String user, String mail, String org, String res, String passw,
                           String outputFolder, String opensslPath, String caconfPath)
            throws IOException, InterruptedException;

    void revokeCertificate(String certificateFileName, String outputFolder, String opensslPath,
                                  String caconfPath)
            throws IOException, InterruptedException;

    void sendEmail(String userEmail) throws MessagingException;
}