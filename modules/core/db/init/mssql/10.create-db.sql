-- begin ACCOUNTANDCERTIFICATEMANAGER_NOTIFICATION
create table ACCOUNTANDCERTIFICATEMANAGER_NOTIFICATION (
    ID uniqueidentifier,
    VERSION integer not null,
    CREATE_TS datetime2,
    CREATED_BY varchar(50),
    UPDATE_TS datetime2,
    UPDATED_BY varchar(50),
    DELETE_TS datetime2,
    DELETED_BY varchar(50),
    --
    CHECK_DATE datetime2,
    USER_ID uniqueidentifier not null,
    TOPIC varchar(512),
    MESSAGE varchar(1024),
    --
    primary key nonclustered (ID)
)^
-- end ACCOUNTANDCERTIFICATEMANAGER_NOTIFICATION
-- begin ACCOUNTANDCERTIFICATEMANAGER_REVOKE
create table ACCOUNTANDCERTIFICATEMANAGER_REVOKE (
    ID uniqueidentifier,
    VERSION integer not null,
    CREATE_TS datetime2,
    CREATED_BY varchar(50),
    UPDATE_TS datetime2,
    UPDATED_BY varchar(50),
    DELETE_TS datetime2,
    DELETED_BY varchar(50),
    --
    REASON varchar(255) not null,
    --
    primary key nonclustered (ID)
)^
-- end ACCOUNTANDCERTIFICATEMANAGER_REVOKE
-- begin ACCOUNTANDCERTIFICATEMANAGER_CERTIFICATE
create table ACCOUNTANDCERTIFICATEMANAGER_CERTIFICATE (
    ID uniqueidentifier,
    VERSION integer not null,
    CREATE_TS datetime2,
    CREATED_BY varchar(50),
    UPDATE_TS datetime2,
    UPDATED_BY varchar(50),
    DELETE_TS datetime2,
    DELETED_BY varchar(50),
    --
    USER_ID uniqueidentifier not null,
    DURATION_DAYS bigint not null,
    REVOKE_ID uniqueidentifier,
    --
    primary key nonclustered (ID)
)^
-- end ACCOUNTANDCERTIFICATEMANAGER_CERTIFICATE
