package com.company.accountandcertificatemanager.service;

import com.haulmont.cuba.security.entity.User;

import java.io.Serializable;

public interface NotificationService {
    String NAME = "accountandcertificatemanager_NotificationService";

    void fireNotification(NotificationInfo notificationInfo, User user);

    Integer getCountNotificationsByUser(User user);

    class NotificationInfo implements Serializable {
        private String topic;
        private String description;

        public NotificationInfo(String topic, String description) {
            this.topic = topic;
            this.description = description;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }
}