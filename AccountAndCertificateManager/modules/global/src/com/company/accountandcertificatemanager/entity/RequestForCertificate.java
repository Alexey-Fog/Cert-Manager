package com.company.accountandcertificatemanager.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Table(name = "ACCOUNTANDCERTIFICATEMANAGER_REQUEST_FOR_CERTIFICATE")
@Entity(name = "accountandcertificatemanager_RequestForCertificate")
public class RequestForCertificate extends StandardEntity {
    private static final long serialVersionUID = -1882004237651711911L;

    @Transient
    @NotNull
    protected String typeOfRequestForCertificate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    protected User user;

    @NotNull
    @Email
    @Column(name = "EMAIL")
    protected String email;

    @NotNull
    @Column(name = "RESOURCE")
    protected String resource;

    @NotNull
    @Column(name="DATE_FROM")
    protected LocalDate dateFrom;

    @NotNull
    @Column(name="DATE_TO")
    protected LocalDate dateTo;

    public TypeOfRequestForCert getTypeOfRequestForCertificate() {
        return TypeOfRequestForCert.fromId(typeOfRequestForCertificate);
    }

    public void setTypeOfRequestForCertificate(TypeOfRequestForCert typeOfRequestForCertificate) {
        this.typeOfRequestForCertificate = typeOfRequestForCertificate == null ?
                TypeOfRequestForCert.Create.getId() : typeOfRequestForCertificate.getId();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
}