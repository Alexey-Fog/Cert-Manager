package com.company.accountandcertificatemanager.listeners;

import com.company.accountandcertificatemanager.entity.Notification;

import java.util.UUID;

import com.company.accountandcertificatemanager.events.NotificationEvent;
import com.company.accountandcertificatemanager.service.NotificationService;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.global.Events;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;

@Component("accountandcertificatemanager_NotificationChangedListener")
public class NotificationChangedListener {

    @Inject
    private UserSessionSource userSessionSource;
    @Inject
    private NotificationService notificationService;
    @Inject
    private Events events;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(EntityChangedEvent<Notification, UUID> event) {
        User currentUser = userSessionSource.getUserSession().getUser();
        Integer count = notificationService.getCountNotificationsByUser(currentUser);
        events.publish(new NotificationEvent(
                this,
                currentUser.getLogin()
                ));
    }
}