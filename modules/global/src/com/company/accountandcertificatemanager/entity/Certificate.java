package com.company.accountandcertificatemanager.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;
import com.haulmont.cuba.security.entity.User;
import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@PublishEntityChangedEvents
@Table(name = "ACCOUNTANDCERTIFICATEMANAGER_CERTIFICATE")
@Entity(name = "accountandcertificatemanager_Certificate")
@NamePattern("%s|createdBy")
public class Certificate extends StandardEntity {
    private static final long serialVersionUID = 3858656151368916273L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name="DURATION_DAYS", nullable = false)
    @Positive
    private Long durationDays;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REVOKE_ID")
    private Revoke revoked = null;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Long getDurationDays() {
        return durationDays;
    }
    public void setDurationDays(Long durationDays) {
        this.durationDays = durationDays;
    }
    public void setDurationDays(Date endDate) {
        long diff = endDate.getTime() - createTs.getTime();
        durationDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
    public Revoke getRevoked() {
        return revoked;
    }
    public void setRevoked(Revoke revoked) {
        this.revoked = revoked;
    }

    @MetaProperty(related = {"createTs", "durationDays"})
    public Date getEndDate() {
        if (createTs == null) return null;

        Calendar c = Calendar.getInstance();
        c.setTime(createTs);
        c.add(Calendar.DATE, durationDays.intValue());
        return c.getTime();
    }

    @MetaProperty(related = {"revoked"})
    public String getRevokedFormatted() {
        if (revoked != null) {
            return String.format("%s \n%s \nBy: %s",
                    revoked.getCreateTs(),
                    revoked.getReason(),
                    revoked.getCreatedBy());
        }
        return null;
    }
}