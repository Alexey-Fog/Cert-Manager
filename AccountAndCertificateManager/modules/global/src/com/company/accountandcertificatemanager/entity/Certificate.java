package com.company.accountandcertificatemanager.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Table(name = "ACCOUNTANDCERTIFICATEMANAGER_CERTIFICATE")
@Entity(name = "accountandcertificatemanager_Certificate")
public class Certificate extends StandardEntity {
    private static final long serialVersionUID = 3858656151368916273L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXTUSER_ID")
    protected ExtUser user;

    @NotNull
    @Column(name="DATE_FROM")
    protected LocalDate dateFrom;

    @NotNull
    @Column(name="DATE_TO")
    protected LocalDate dateTo;

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