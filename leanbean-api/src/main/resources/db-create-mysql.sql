drop database if exists leanbean;
create database if not exists leanbean;

use leanbean;

create table if not exists user (
  id integer not null auto_increment,
  first_name varchar(256) not null,
  last_name varchar(256) not null,
  email varchar(256) not null,
  primary key (id)
) engine=innodb;

create table if not exists meeting (
  id integer not null auto_increment,
  title varchar(256) not null,
  user_id integer not null,
  start datetime null,
  duration int null,
  primary key (id),
  index user_index (user_id),
  foreign key (user_id)
    references user(id)
    on delete cascade
) engine=innodb;

create table if not exists topic (
  id integer not null auto_increment,
  meeting_id integer not null,
  user_id integer not null,
  title varchar(256) not null,
  pitch varchar(256) not null,
  primary key (id),
  index meeting_index (meeting_id),
  index user_index (user_id),
  foreign key (meeting_id)
    references meeting(id)
    on delete cascade,
  foreign key (user_id)
  references user(id)
    on delete no action
) engine innodb;

create table if not exists vote (
  id integer not null auto_increment,
  meeting_id integer not null,
  user_id integer not null,
  topic_id integer not null,
  primary key (id),
  index meeting_index (meeting_id),
  index user_index (user_id),
  index topic_index (topic_id),
  foreign key (meeting_id)
    references meeting(id)
    on delete cascade,
  foreign key (user_id)
    references user(id)
    on delete no action,
  foreign key (topic_id)
    references topic(id)
    on delete cascade
) engine innodb;