create table Account (
id bigint not null auto_increment primary key,
surname varchar(50) not null,
name varchar(50) not null,
father varchar(50) default '',
sum float default 0.0
);

insert into account (surname, name, father, sum) values ('Pupkin', 'Vasiliy', 'Sergeevich', 100345);
insert into account (surname, name, father, sum) values ('Ivanov', 'Ivan', 'Ivanovich', 543000);
insert into account (surname, name, father, sum) values ('Peterson', 'James', '', 10000);

create table Operation (
id bigint not null auto_increment primary key,
sourceAccId bigint not null,
destAccId bigint not null,
sum float default 0.0,
date datetime not null,
FOREIGN KEY (sourceAccId) REFERENCES Account(id),
FOREIGN KEY (destAccId) REFERENCES Account(id)
);

insert into operation (sourceAccId, destAccId, sum, date) values (1, 2, 100, '2004-05-06');
insert into operation (sourceAccId, destAccId, sum, date) values (2, 3, 200, '2004-05-06');
insert into operation (sourceAccId, destAccId, sum, date) values (3, 1, 500, '2004-05-06');