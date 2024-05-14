package com.company.accountandcertificatemanager.web.screens.certificate;

import com.haulmont.cuba.gui.screen.*;
import com.company.accountandcertificatemanager.entity.Certificate;

@UiController("accountandcertificatemanager_Certificate.browse")
@UiDescriptor("certificate-browse.xml")
@LookupComponent("certificatesTable")
@LoadDataBeforeShow
public class CertificateBrowse extends StandardLookup<Certificate> {
}