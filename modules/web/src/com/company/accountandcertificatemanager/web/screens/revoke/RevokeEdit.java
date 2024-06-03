package com.company.accountandcertificatemanager.web.screens.revoke;

import com.company.accountandcertificatemanager.entity.Certificate;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.company.accountandcertificatemanager.entity.Revoke;

import javax.inject.Inject;

@UiController("accountandcertificatemanager_Revoke.edit")
@UiDescriptor("revoke-edit.xml")
@EditedEntityContainer("revokeDc")
@LoadDataBeforeShow
public class RevokeEdit extends StandardEditor<Revoke> {

}