package com.company.accountandcertificatemanager.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum TypeOfRequestForCert implements EnumClass<String> {

    Create("Создать"),
    Renew("Обновить"),
    Revoke("Отозвать");

    private String id;

    TypeOfRequestForCert(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static TypeOfRequestForCert fromId(String id) {
        for (TypeOfRequestForCert at : TypeOfRequestForCert.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}