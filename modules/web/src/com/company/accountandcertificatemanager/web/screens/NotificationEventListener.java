package com.company.accountandcertificatemanager.web.screens;

import com.company.accountandcertificatemanager.events.NotificationEvent;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.Notifications;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class NotificationEventListener {

    @Inject
    private UserSessionSource userSessionSource;
    @Inject
    private Notifications notifications;

    @EventListener
    public void onNotificationEvent(NotificationEvent event) {
        if(event.getUserLogin() == userSessionSource.getUserSession().getUser().getLogin())
        notifications.create()
                .withCaption("+1 Новое уведомление")
                .withDescription("Не прочитанных уведомлений: ")
                .withType(Notifications.NotificationType.TRAY)
                .show();
    }
}
