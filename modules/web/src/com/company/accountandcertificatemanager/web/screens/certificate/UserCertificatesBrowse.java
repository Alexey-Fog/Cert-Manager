package com.company.accountandcertificatemanager.web.screens.certificate;

import com.company.accountandcertificatemanager.entity.Revoke;
import com.company.accountandcertificatemanager.service.NotificationService;
import com.company.accountandcertificatemanager.service.OpenSSLService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Button;
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
    @Inject
    private DataManager dataManager;
    @Inject
    private OpenSSLService openSSLService;
    @Inject
    private NotificationService notificationService;

    @Subscribe
    public void onInit(InitEvent event) {
        if (userField.getValue() == null) userField.setValue(userSessionSource.getUserSession().getUser());
        certificatesDl.setParameter("ulogin", userField.getValue().getLoginLowerCase());
        userField.addValueChangeListener(userValueChangeEvent -> {
            certificatesDl.setParameter("ulogin", userField.getValue().getLoginLowerCase());
            certificatesDl.load();
        });
        certificatesTable.addSelectionListener(
                certificateSelectionEvent ->
                    btnRevoke.setEnabled(!certificatesTable.getSelected().isEmpty() &&
                            certificatesTable.getSingleSelected().getRevoked() == null)
        );
        certificatesDl.setParameter("ulogin", userField.getValue().getLoginLowerCase());
        certificatesDl.load();
        btnRevoke.setEnabled(!certificatesTable.getSelected().isEmpty() &&
                certificatesTable.getSingleSelected().getRevoked() == null);
    }

    @Subscribe("btnCreate")
    public void onBtnCreateClick(Button.ClickEvent event) {
        User user = userField.getValue();
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            dialogs.createMessageDialog().withCaption("Information").withMessage(messages.getMessage(getClass(), "exEmail")).show();
            return;
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            dialogs.createMessageDialog().withCaption("Information").withMessage(messages.getMessage(getClass(), "exName")).show();
            return;
        }
        Screen editorScreen = screenBuilders.editor(Certificate.class, this)
                .newEntity()
                .withInitializer(certificate -> certificate.setUser(user))
                .withScreenClass(CertificateEdit.class)
                .withOpenMode(OpenMode.DIALOG)
                .build();
        editorScreen.addAfterCloseListener(afterCloseEvent -> {
            certificatesDl.load();
        });
        editorScreen.show();
    }

    @Subscribe("btnRevoke")
    public void onBtnRevokeClick(Button.ClickEvent event) {
        Certificate selectedCertificate = certificatesTable.getSingleSelected();
        Revoke newRevoke = dataManager.create(Revoke.class);
        Screen editorScreen = screenBuilders.editor(Revoke.class, this)
                .newEntity(newRevoke)
                .withOpenMode(OpenMode.DIALOG)
                .build();
        editorScreen.addAfterCloseListener(afterCloseEvent -> {
            if (afterCloseEvent.getCloseAction() == WINDOW_COMMIT_AND_CLOSE_ACTION) {
                try {
                    openSSLService.revokeCertificate(
                            selectedCertificate.getUser().getName(),
                            "C:\\certs\\maimn.crt",  // заменить
                            "C:\\certs",  // заменить
                            "D:\\OpenSSL-Win64\\bin\\openssl.exe",  // заменить
                            "C:\\certs\\ca.conf"  // заменить
                    );
                    selectedCertificate.setRevoked(newRevoke);
                    dataManager.commit(selectedCertificate);
                    createNotification(
                            "Сертификат отозван",
                            "Сертификат отозван пользователем: " + userSessionSource.getUserSession().getUser(),
                            selectedCertificate.getUser());
                } catch (Exception e) {
                    dataManager.remove(newRevoke);
                    dialogs.createMessageDialog()
                            .withCaption("Information")
                            .withMessage(messages.getMessage(getClass(), "revokeExeption"))
                            .show();
                }
                certificatesDl.load();
            }
        });
        editorScreen.show();
    }

    private void createNotification(String topic, String message, User user) {
        notificationService.fireNotification(new NotificationService.NotificationInfo(topic, message), user);
    }
}