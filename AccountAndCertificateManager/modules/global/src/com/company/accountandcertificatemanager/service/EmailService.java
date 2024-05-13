package com.company.accountandcertificatemanager.service;

public interface EmailService {
    String NAME = "accountandcertificatemanager_EmailService";

    void sendCertificatesByEmail(String userEmail, String[] certificateFiles);
}