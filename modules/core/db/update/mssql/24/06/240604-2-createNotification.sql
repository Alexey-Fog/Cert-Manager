alter table ACCOUNTANDCERTIFICATEMANAGER_NOTIFICATION add constraint FK_ACCOUNTANDCERTIFICATEMANAGER_NOTIFICATION_ON_USER foreign key (USER_ID) references SEC_USER(ID);
create index IDX_ACCOUNTANDCERTIFICATEMANAGER_NOTIFICATION_ON_USER on ACCOUNTANDCERTIFICATEMANAGER_NOTIFICATION (USER_ID);
