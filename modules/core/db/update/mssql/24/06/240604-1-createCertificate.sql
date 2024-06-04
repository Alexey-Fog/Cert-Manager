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
);