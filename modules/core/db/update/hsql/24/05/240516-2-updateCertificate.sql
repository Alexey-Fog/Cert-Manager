alter table ACCOUNTANDCERTIFICATEMANAGER_CERTIFICATE alter column REVOKED rename to REVOKED__U99304 ^
alter table ACCOUNTANDCERTIFICATEMANAGER_CERTIFICATE alter column REVOKED__U99304 set null ;
alter table ACCOUNTANDCERTIFICATEMANAGER_CERTIFICATE add column REVOKE_ID varchar(36) ;