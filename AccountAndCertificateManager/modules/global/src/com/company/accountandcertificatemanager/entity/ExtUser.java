package com.company.accountandcertificatemanager.entity;

import com.haulmont.cuba.security.entity.User;

import javax.annotation.Nullable;
import javax.persistence.*;

@Table(name = "ACCOUNTANDCERTIFICATEMANAGER_EXT_USER")
@Entity(name = "accountandcertificatemanager_ExtUser")
public class ExtUser extends User {
    private static final long serialVersionUID = 6659481362332810788L;

    @Nullable
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CERTIFICATE_ID")
    protected Certificate activeCertificate;

}