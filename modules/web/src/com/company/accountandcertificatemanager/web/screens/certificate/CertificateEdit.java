package com.company.accountandcertificatemanager.web.screens.certificate;

import com.company.accountandcertificatemanager.service.OpenSSLService;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.company.accountandcertificatemanager.entity.Certificate;

import javax.inject.Inject;
import javax.mail.MessagingException;
import java.io.IOException;

@UiController("accountandcertificatemanager_Certificate.edit")
@UiDescriptor("certificate-edit.xml")
@EditedEntityContainer("certificateDc")
@LoadDataBeforeShow
public class CertificateEdit extends StandardEditor<Certificate> {
    @Inject
    private OpenSSLService openSSLService;

    @Subscribe("emailBtn")
    public void onEmailBtnClick(Button.ClickEvent event) throws MessagingException {
        openSSLService.sendEmail("alpetrov.2003@bk.ru");
    }
}