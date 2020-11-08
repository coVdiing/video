drop table if exists `test`
create table `test`(
    `id` varchar(8) not null,
    `name` varchar(50) default null,
    primary key (`id`)
)engine=innodb default charset=utf8mb4 comment='测试';

insert into `test` values(1,'刘备');
insert into `test` values(2,'关羽');
