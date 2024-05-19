package com.company.accountandcertificatemanager.web.screens.revoke;

import com.haulmont.cuba.gui.screen.*;
import com.company.accountandcertificatemanager.entity.Revoke;

@UiController("accountandcertificatemanager_Revoke.edit")
@UiDescriptor("revoke-edit.xml")
@EditedEntityContainer("revokeDc")
@LoadDataBeforeShow
public class RevokeEdit extends StandardEditor<Revoke> {
}