-- begin ACCOUNTANDCERTIFICATEMANAGER_CERTIFICATE
create table ACCOUNTANDCERTIFICATEMANAGER_CERTIFICATE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    USER_ID varchar(36) not null,
    DURATION_DAYS bigint not null,
    REVOKE_ID varchar(36),
    --
    primary key (ID)
)^
-- end ACCOUNTANDCERTIFICATEMANAGER_CERTIFICATE
-- begin ACCOUNTANDCERTIFICATEMANAGER_REVOKE
create table ACCOUNTANDCERTIFICATEMANAGER_REVOKE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    REASON varchar(255) not null,
    --
    primary key (ID)
)^
-- end ACCOUNTANDCERTIFICATEMANAGER_REVOKE
