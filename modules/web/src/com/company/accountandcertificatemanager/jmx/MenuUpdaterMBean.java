package com.company.accountandcertificatemanager.jmx;

import org.springframework.jmx.export.annotation.ManagedResource;
import com.haulmont.cuba.core.sys.jmx.JmxBean;

@JmxBean(module = "accountandcertificatemanager", alias = "menuUpdater")
@ManagedResource(description = "JMX bean for some settings")
public interface MenuUpdaterMBean {
}