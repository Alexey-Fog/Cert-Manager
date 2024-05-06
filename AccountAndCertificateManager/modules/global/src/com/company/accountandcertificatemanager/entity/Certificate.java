package com.company.accountandcertificatemanager.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Table(name = "ACCOUNTANDCERTIFICATEMANAGER_CERTIFICATE")
@Entity(name = "accountandcertificatemanager_Certificate")
public class Certificate extends StandardEntity {
    private static final long serialVersionUID = 3858656151368916273L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    protected User user;

    @NotNull
    @Column(name = "RESOURCE")
    protected String resource;

    @NotNull
    @Column(name="DATE_FROM")
    protected LocalDate dateFrom;

    @NotNull
    @Column(name="DATE_TO")
    protected LocalDate dateTo;




    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}