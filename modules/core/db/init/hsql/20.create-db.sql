-- begin ACCOUNTANDCERTIFICATEMANAGER_CERTIFICATE
alter table ACCOUNTANDCERTIFICATEMANAGER_CERTIFICATE add constraint FK_ACCOUNTANDCERTIFICATEMANAGER_CERTIFICATE_ON_USER foreign key (USER_ID) references SEC_USER(ID)^
create index IDX_ACCOUNTANDCERTIFICATEMANAGER_CERTIFICATE_ON_USER on ACCOUNTANDCERTIFICATEMANAGER_CERTIFICATE (USER_ID)^
-- end ACCOUNTANDCERTIFICATEMANAGER_CERTIFICATE