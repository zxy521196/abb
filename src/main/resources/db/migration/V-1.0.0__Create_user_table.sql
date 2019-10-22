create table user (
    ID int  primary key auto_increment,
    NAME varchar(100),
    TOKEN char(36) not null
);