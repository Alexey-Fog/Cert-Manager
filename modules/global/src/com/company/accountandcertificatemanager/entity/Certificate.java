package com.company.accountandcertificatemanager.entity;

import com.esotericsoftware.kryo.NotNull;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Table(name = "ACCOUNTANDCERTIFICATEMANAGER_CERTIFICATE")
@Entity(name = "accountandcertificatemanager_Certificate")
public class Certificate extends StandardEntity {
    private static final long serialVersionUID = 3858656151368916273L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @NotNull
    @Column(name="DURATION_DAYS", nullable = false)
    @Positive
    private Long durationDays;

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

    public void setEndDate(Date endDate) {
        long diff = endDate.getTime() - createTs.getTime();
        durationDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Transient
    @MetaProperty(related = {"createTs", "durationDays"})
    public Date getEndDate() {
        if (createTs == null) return null;

        Calendar c = Calendar.getInstance();
        c.setTime(createTs);
        c.add(Calendar.DATE, durationDays.intValue());
        return c.getTime();
    }

}