create table patients(

    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null,
    phone varchar(20) not null,
    document varchar(15) not null,
    street varchar(100) not null,
    number varchar(20) not null,
    city varchar(100) not null,
    active tinyint not null,

    primary key(id)

);