package com.company.accountandcertificatemanager.web.screens.certificate;

import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Filter;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.accountandcertificatemanager.entity.Certificate;
import com.haulmont.cuba.security.entity.User;

import javax.inject.Inject;

@UiController("accountandcertificatemanager_UserCertificates.browse")
@UiDescriptor("user-certificates-browse.xml")
@LookupComponent("certificatesTable")
@LoadDataBeforeShow
public class UserCertificatesBrowse extends StandardLookup<Certificate> {
    @Inject
    private PickerField<User> userField;
    @Inject
    private CollectionLoader<Certificate> certificatesDl;
    @Inject
    private UserSessionSource userSessionSource;
    @Inject
    private Button btnRevoke;
    @Inject
    private GroupTable<Certificate> certificatesTable;
    @Inject
    private Dialogs dialogs;
    @Inject
    protected Messages messages;
    @Inject
    private ScreenBuilders screenBuilders;

    @Subscribe
    public void onInit(InitEvent event) {
        userField.setValue(userSessionSource.getUserSession().getUser());
        certificatesDl.setParameter("ulogin", userField.getValue().getLoginLowerCase());
        userField.addValueChangeListener(userValueChangeEvent -> {
            certificatesDl.setParameter("ulogin", userField.getValue().getLoginLowerCase());
            certificatesDl.load();
        });

        certificatesTable.addSelectionListener(
                certificateSelectionEvent -> btnRevoke.setEnabled(!certificatesTable.getSelected().isEmpty())
        );
    }

    public void onCreateClick() {
        User user = userField.getValue();
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            dialogs.createMessageDialog().withCaption("Information").withMessage(messages.getMessage(getClass(), "exEmail")).show();
            return;
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            dialogs.createMessageDialog().withCaption("Information").withMessage(messages.getMessage(getClass(), "exName")).show();
            return;
        }
        screenBuilders.editor(Certificate.class, this)
                .newEntity()
                .withInitializer(certificate -> certificate.setUser(user))
                .withScreenClass(CertificateEdit.class)
                .withOpenMode(OpenMode.DIALOG)
                .build()
                .show();
    }
}