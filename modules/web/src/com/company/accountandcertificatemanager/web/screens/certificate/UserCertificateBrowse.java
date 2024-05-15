package com.company.accountandcertificatemanager.web.screens.certificate;

import com.haulmont.cuba.gui.screen.*;
import com.company.accountandcertificatemanager.entity.Certificate;

@UiController("accountandcertificatemanager_UserCertificate.browse")
@UiDescriptor("user-certificate-browse.xml")
@LookupComponent("certificatesTable")
@LoadDataBeforeShow
public class UserCertificateBrowse extends StandardLookup<Certificate> {
}