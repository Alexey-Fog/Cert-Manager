package com.company.accountandcertificatemanager.service;

import com.company.accountandcertificatemanager.entity.Notification;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(NotificationService.NAME)
public class NotificationServiceBean implements NotificationService {
    @Inject
    private DataManager dataManager;

    @Override
    public void fireNotification(NotificationInfo notificationInfo, User user) {
        Notification notification = createNotification(notificationInfo, user);
        dataManager.commit(notification);
    }

    private Notification createNotification(NotificationInfo notificationInfo, User user) {
        Notification notification = dataManager.create(Notification.class);
        notification.setTopic(notificationInfo.getTopic());
        notification.setMessage(notificationInfo.getDescription());
        notification.setUser(user);
        return notification;
    }

    @Override
    public Integer getCountNotificationsByUser(User user) {
        return dataManager.loadValue("select count(e) from accountandcertificatemanager_Notification e where e.user = :user and e.checkDate is null", Integer.class)
                .parameter("user", user)
                .optional()
                .orElse(0);
    }
}