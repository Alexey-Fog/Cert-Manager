package com.company.accountandcertificatemanager.web.screens.notification;

import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.core.global.queryconditions.Condition;
import com.haulmont.cuba.core.global.queryconditions.JpqlCondition;
import com.haulmont.cuba.core.global.queryconditions.LogicalCondition;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.CheckBox;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.accountandcertificatemanager.entity.Notification;
import com.haulmont.cuba.security.entity.User;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@UiController("accountandcertificatemanager_Notification.browse")
@UiDescriptor("notification-browse.xml")
@LookupComponent("notificationsTable")
@LoadDataBeforeShow
public class NotificationBrowse extends StandardLookup<Notification> {
    @Inject
    private UserSessionSource userSessionSource;
    @Inject
    private CollectionLoader<Notification> notificationsDl;
    private User currentUser;
    @Inject
    private CheckBox withReadNotificationsCheckBox;
    private final JpqlCondition condition = new JpqlCondition("e.checkDate is null");
    @Inject
    private DataManager dataManager;
    @Inject
    private GroupTable<Notification> notificationsTable;
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private CollectionContainer<Notification> notificationsDc;


    @Subscribe
    public void onInit(InitEvent event) {
        currentUser = userSessionSource.getUserSession().getCurrentOrSubstitutedUser();
        notificationsDl.setParameter("currentUser", currentUser);
    }

    @Install(to = "notificationsDl", target = Target.DATA_LOADER)
    private List<Notification> notificationsDlLoadDelegate(LoadContext<Notification> loadContext) {
        LogicalCondition queryCondition = (LogicalCondition) loadContext.getQuery().getCondition();
        List<Condition> conditionList = queryCondition.getConditions();
        if (!withReadNotificationsCheckBox.getValue()) {
            if (!conditionList.contains(condition)) {
                queryCondition.add(condition);
            }
        } else {
            conditionList.remove(condition);
        }
        loadContext.getQuery().setCondition(queryCondition);
        return dataManager.loadList(loadContext);
    }

    @Install(to = "notificationsTable", subject = "lookupSelectHandler")
    private void notificationsTableLookupSelectHandler(Collection<Notification> collection) {
        Notification singleSelected = notificationsTable.getSingleSelected();
        Screen build = screenBuilders.editor(Notification.class, this)
                .editEntity(singleSelected)
                .build();
        if (singleSelected.getCheckDate() == null) {
            build.addAfterCloseListener(afterCloseEvent -> {
                singleSelected.setCheckDate(new Date(System.currentTimeMillis()));
                dataManager.commit(singleSelected);
                notificationsDl.load();
            });
        }
        build.show();
    }

    @Subscribe("withReadNotificationsCheckBox")
    public void onWithReadNotificationsCheckBoxValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        notificationsDl.load();
    }

    @Subscribe("markAllChecked")
    public void onMarkAllCheckedClick(Button.ClickEvent event) {
        Date now = new Date(System.currentTimeMillis());
        CommitContext commitContext = new CommitContext();
        for (Notification notification : notificationsDc.getMutableItems()) {
            if (notification.getCheckDate() == null) {
                notification.setCheckDate(now);
                commitContext.addInstanceToCommit(notification);
            }
        }
        dataManager.commit(commitContext);
        notificationsDl.load();
    }
}