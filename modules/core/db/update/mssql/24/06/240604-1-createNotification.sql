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
);