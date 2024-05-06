package com.company.accountandcertificatemanager.service;

import java.io.IOException;

public interface OpenSSLService {
    String NAME = "accountandcertificatemanager_OpenSSLService";

    void generateCertificate(String user, String mail, String org, String res, String passw,
                             String outputFolder, String opensslPath) throws IOException, InterruptedException;

    void sendCertificateByEmail(String user, String mail);
}