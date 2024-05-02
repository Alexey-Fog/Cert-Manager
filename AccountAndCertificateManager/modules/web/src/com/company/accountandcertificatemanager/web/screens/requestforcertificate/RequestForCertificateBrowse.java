package com.company.accountandcertificatemanager.web.screens.requestforcertificate;

import com.haulmont.cuba.gui.screen.*;
import com.company.accountandcertificatemanager.entity.RequestForCertificate;

@UiController("accountandcertificatemanager_RequestForCertificate.browse")
@UiDescriptor("request-for-certificate-browse.xml")
@LookupComponent("requestForCertificatesTable")
@LoadDataBeforeShow
public class RequestForCertificateBrowse extends StandardLookup<RequestForCertificate> {
}