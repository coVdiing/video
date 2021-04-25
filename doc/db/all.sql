drop table if exists `chapter`;
create table `chapter` (
`id` char(8) not null comment 'ID',
`course_id` char(8) comment '课程ID',
`name` varchar(50) comment '名称',
`gmt_create` datetime NOT NULL COMMENT '创建时间',
`gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
primary key(`id`)
)engine=innodb default charset=utf8mb4 comment='大章';

insert into `chapter` (id, course_id, name,gmt_create) values ('00000000', '00000000', '测试大章00',now());
insert into `chapter` (id, course_id, name,gmt_create) values ('00000001', '00000000', '测试大章01',now());
insert into `chapter` (id, course_id, name,gmt_create) values ('00000003', '00000000', '测试大章03',now());
insert into `chapter` (id, course_id, name,gmt_create) values ('00000004', '00000000', '测试大章04',now());
insert into `chapter` (id, course_id, name,gmt_create) values ('00000005', '00000000', '测试大章05',now());
insert into `chapter` (id, course_id, name,gmt_create) values ('00000006', '00000000', '测试大章06',now());
insert into `chapter` (id, course_id, name,gmt_create) values ('00000007', '00000000', '测试大章07',now());
insert into `chapter` (id, course_id, name,gmt_create) values ('00000008', '00000000', '测试大章08',now());
insert into `chapter` (id, course_id, name,gmt_create) values ('00000009', '00000000', '测试大章09',now());
insert into `chapter` (id, course_id, name,gmt_create) values ('00000010', '00000000', '测试大章10',now());
insert into `chapter` (id, course_id, name,gmt_create) values ('00000011', '00000000', '测试大章11',now());
insert into `chapter` (id, course_id, name,gmt_create) values ('00000012', '00000000', '测试大章12',now());
insert into `chapter` (id, course_id, name,gmt_create) values ('00000013', '00000000', '测试大章13',now());
insert into `chapter` (id, course_id, name,gmt_create) values ('00000014', '00000000', '测试大章14',now());

-- 小节
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
    `id` CHAR(8) NOT NULL DEFAULT '' COMMENT 'ID',
    `title` VARCHAR(50) NOT NULL COMMENT '标题',
    `course_id` CHAR(8) COMMENT '课程|curse.id',
    `chapter_id` CHAR(8) COMMENT '大章|chapter.id',
    `video` VARCHAR(200) COMMENT '视频',
    `time` INT COMMENT '时长|单位秒',
    `charge` CHAR(1) COMMENT '收费|c 收费; f 免费',
    `sort` INT COMMENT '顺序',
    `gmt_create` DATETIME COMMENT '创建时间',
    `gmt_modified` DATETIME COMMENT '修改时间',
    PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='小节';

insert into `section` (id, title, course_id, chapter_id, video, time, charge, sort, gmt_create, gmt_modified)
values ('00000001', '测试小节01', '00000001', '00000000', '', 500, 'f', 1, now(), now());


----------------测试

drop table if exists `test`
create table `test`(
    `id` varchar(8) not null,
    `name` varchar(50) default null,
    primary key (`id`)
)engine=innodb default charset=utf8mb4 comment='测试';

insert into `test` values(1,'刘备');
insert into `test` values(2,'关羽');
