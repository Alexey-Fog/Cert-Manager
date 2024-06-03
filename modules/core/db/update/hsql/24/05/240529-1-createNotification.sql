create table ACCOUNTANDCERTIFICATEMANAGER_NOTIFICATION (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CHECK_DATE timestamp,
    REMIND boolean,
    USER_ID varchar(36) not null,
    TOPIC varchar(512),
    MESSAGE varchar(1024),
    --
    primary key (ID)
);