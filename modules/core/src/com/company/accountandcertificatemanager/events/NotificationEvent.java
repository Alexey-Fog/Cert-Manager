package com.company.accountandcertificatemanager.events;

import org.springframework.context.ApplicationEvent;

public class NotificationEvent extends ApplicationEvent {
    private String userLogin;

    public NotificationEvent(Object source, String userLogin) {
        super(source);
        this.userLogin = userLogin;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
