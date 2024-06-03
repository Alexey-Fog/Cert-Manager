package com.company.accountandcertificatemanager.entity;

import com.esotericsoftware.kryo.NotNull;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "ACCOUNTANDCERTIFICATEMANAGER_REVOKE")
@Entity(name = "accountandcertificatemanager_Revoke")
@NamePattern("%s|createdBy")
public class Revoke extends StandardEntity {
    private static final long serialVersionUID = 968179161152136828L;

    @NotNull
    @Column(name="REASON", nullable = false)
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

