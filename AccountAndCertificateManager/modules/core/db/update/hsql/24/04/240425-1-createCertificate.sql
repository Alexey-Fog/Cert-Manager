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
    USER_ID varchar(36),
    EMAIL varchar(255),
    RESOURCE varchar(255),
    DATE_FROM date,
    DATE_TO date,
    --
    primary key (ID)
);