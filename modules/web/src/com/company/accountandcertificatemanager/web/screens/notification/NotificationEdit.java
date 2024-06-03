package com.company.accountandcertificatemanager.web.screens.notification;

import com.haulmont.cuba.gui.screen.*;
import com.company.accountandcertificatemanager.entity.Notification;

@UiController("accountandcertificatemanager_Notification.edit")
@UiDescriptor("notification-edit.xml")
@EditedEntityContainer("notificationDc")
@LoadDataBeforeShow
public class NotificationEdit extends StandardEditor<Notification> {

}