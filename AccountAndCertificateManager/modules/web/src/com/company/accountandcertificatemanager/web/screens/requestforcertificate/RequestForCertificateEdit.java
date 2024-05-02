package com.company.accountandcertificatemanager.web.screens.requestforcertificate;

import com.haulmont.cuba.gui.screen.*;
import com.company.accountandcertificatemanager.entity.RequestForCertificate;

@UiController("accountandcertificatemanager_RequestForCertificate.edit")
@UiDescriptor("request-for-certificate-edit.xml")
@EditedEntityContainer("requestForCertificateDc")
@LoadDataBeforeShow
public class RequestForCertificateEdit extends StandardEditor<RequestForCertificate> {
}