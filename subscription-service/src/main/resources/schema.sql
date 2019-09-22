create sequence campaign_id_seq;
create sequence subscriber_id_seq;

create table campaign
(
    id integer default nextval('public.campaign_id_seq') not null ,
    name varchar(255) not null,
    primary key (id)
);

create table subscriber
(
    id integer default nextval('public.subscriber_id_seq') not null,
    first_name varchar(255),
    gender varchar(8),
    email varchar (255) not null,
    date_of_birth varchar(10) not null
);

create table campaign_subscriber(
    campaign_id integer not null,
    subscriber_id integer not null,
    primary key (campaign_id, subscriber_id),
    foreign key (subscriber_id) references subscriber(id),
    foreign key (campaign_id) references campaign(id)
);

