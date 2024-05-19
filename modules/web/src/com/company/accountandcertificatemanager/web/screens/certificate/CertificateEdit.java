package com.company.accountandcertificatemanager.web.screens.certificate;

import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.screen.*;
import com.company.accountandcertificatemanager.entity.Certificate;
import com.haulmont.cuba.security.entity.User;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@UiController("accountandcertificatemanager_Certificate.edit")
@UiDescriptor("certificate-edit.xml")
@EditedEntityContainer("certificateDc")
@LoadDataBeforeShow
public class CertificateEdit extends StandardEditor<Certificate> {
    @Inject
    private PickerField<User> userField;
    @Inject
    private CheckBox cbDuration;
    @Inject
    private TextField durationDaysF;
    @Inject
    private CheckBox cbEndDate;
    @Inject
    private DateField<Date> endDateF;
    @Inject
    private TextField<Long> durationDaysField;

    @Subscribe
    public void onInit(InitEvent event) {
        selectSetDuration(true);
        durationDaysF.addValueChangeListener(textChangeEvent -> {
            if (!cbDuration.isChecked()) return;
            if (durationDaysF.getValue() != null && !(durationDaysF.getValue().toString().isEmpty()) && StringUtils.isNumeric(durationDaysF.getValue().toString())) {
                Integer duration = Integer.parseInt(durationDaysF.getValue().toString());
                java.util.Calendar c = java.util.Calendar.getInstance();
                c.setTime(new Date(System.currentTimeMillis()));
                c.add(Calendar.DATE, duration);
                endDateF.setValue(c.getTime());
                durationDaysField.setValue(duration.longValue()+0);
            }
        });
        endDateF.setRangeStart(new Date(System.currentTimeMillis()));
        endDateF.addValueChangeListener(valueChangeEvent -> {
            if (!cbEndDate.isChecked()) return;
            Date endDate = (Date) endDateF.getValue();
            if (endDate != null) {
                long diff = endDate.getTime() - System.currentTimeMillis();
                long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                durationDaysF.setValue(Integer.valueOf((int)days + 1).toString());
                durationDaysField.setValue(days+1);
            }
            else durationDaysF.setValue("");
        });
    }

    @Subscribe
    public void onAfterInit(AfterInitEvent event) {
        if (userField.getValue() != null){
            userField.setVisible(false);
        }
    }

    private Boolean checked = false;

    private void selectSetDuration(Boolean selectD){
        checked = false;
        cbDuration.setValue(selectD);
        cbEndDate.setValue(!selectD);
        durationDaysF.setEnabled(selectD);
        endDateF.setEnabled(!selectD);
        checked = true;
    }

    @Subscribe("cbDuration")
    public void onCbEndDateValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        if (checked) selectSetDuration(cbDuration.isChecked());
    }

    @Subscribe("cbEndDate")
    public void onCbEndDateValueChange1(HasValue.ValueChangeEvent<Boolean> event) {
        if (checked) selectSetDuration(!cbEndDate.isChecked());
    }

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        
    }


}