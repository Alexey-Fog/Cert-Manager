package com.company.accountandcertificatemanager.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import java.util.Date;

@PublishEntityChangedEvents
@Table(name = "ACCOUNTANDCERTIFICATEMANAGER_NOTIFICATION")
@Entity(name = "accountandcertificatemanager_Notification")
public class Notification extends StandardEntity {
    private static final long serialVersionUID = 5350607669184221434L;

    @Column(name = "CHECK_DATE")
    private Date checkDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "TOPIC", length = 512)
    private String topic;

    @Column(name = "MESSAGE", length = 1024)
    private String message;

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}