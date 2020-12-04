
create schema if not exists test collate utf8_general_ci;

create table if not exists user
(
	id bigint not null comment '主键ID'
		primary key,
	name varchar(30) null comment '姓名',
	age int null comment '年龄',
	email varchar(50) null comment '邮箱'
);


insert into test.user (id, name, age, email) values (1, 'Jone', 18, 'test1@gmail.com');
insert into test.user (id, name, age, email) values (2, 'Jack', 20, 'test2@qq.com');
insert into test.user (id, name, age, email) values (3, 'Tom', 28, 'test3@126.com');
insert into test.user (id, name, age, email) values (4, 'Sandy', 21, 'test4@163.com');
insert into test.user (id, name, age, email) values (5, 'Billie', 24, 'test5@qq.com');
