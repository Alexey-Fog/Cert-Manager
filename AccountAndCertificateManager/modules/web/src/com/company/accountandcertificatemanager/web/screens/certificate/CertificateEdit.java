package com.company.accountandcertificatemanager.web.screens.certificate;

import com.haulmont.cuba.gui.screen.*;
import com.company.accountandcertificatemanager.entity.Certificate;

@UiController("accountandcertificatemanager_Certificate.edit")
@UiDescriptor("certificate-edit.xml")
@EditedEntityContainer("certificateDc")
@LoadDataBeforeShow
public class CertificateEdit extends StandardEditor<Certificate> {
}