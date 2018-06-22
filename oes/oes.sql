/*

  管理员：管理注册的教师，数据备份和还原
  
  （1）课程的管理
  （2）班级信息管理
  （3）学生信息管理
  
  教员：
  （1）题库维护
  （2）在线出卷
  （3）自动评阅
  
 学生：
  （1）在线考试
  （2）查看考试成绩和试卷分析
*/
--角色信息表
create table roleInfo(
       rid number(5) primary key,
       rname varchar2(100) not null unique,
       status number(2)
)
--角色信息表序列
create sequence seq_roleInfo_rid start with 1;
--sql语句
drop table roleInfo
select * from roleInfo
delete ffrom roleInfo

--管理员信息表
create table adminInfo(
       aid number(5) primary key,
       rid number(5)
           constraint FK_adminInfo_rid references roleInfo(rid),
       aname varchar2(100) not null,
       pwd varchar2(200) not null,
       email varchar2(100) not null unique,
       photo varchar2(200),
       status number(2)
  )
--管理员信息表序列
create sequence seq_adminInfo_aid start with 1;
--sql语句
drop table adminInfo
select * from adminInfo
delete ffrom adminInfo

--课程信息表
create table courseInfo(
       cid number(5) primary key,
       cname varchar2(100) not null unique,
       semester number(1), -- 开设的学期
       status number(2)
)
--课程信息表序列
create sequence seq_courseInfo_cid start with 1;
--sql语句
drop table courseInfo
select * from courseInfo
delete ffrom courseInfo
--专业表
create table majorInfo(
       mid number(5) primary key,
       mname varchar2(100) not null unique,
       status number(2)
)
--专业信息表序列
create sequence seq_majorInfo_mid start with 1;
--sql语句
drop table courseInfo
select * from courseInfo
delete ffrom courseInfo
--班级信息表
create table classInfo(
       cid number(5) primary key,
       cname varchar2(100) not null,
       mid number(5) 
           constraint FK_classInfo_cid references majorInfo(mid), --所属专业
       grade number(6),--年级
       length number(2),--学制
       status number(2)
  )
  --班级信息表序列
create sequence seq_classInfo_cid start with 1;  
--sql语句
drop table classInfo
select * from classInfo
delete ffrom classInfo
   
--学生信息表   
create table stuInfo(
       sid varchar2(20) primary  key,
       sname varchar2(100) not null,
       pwd varchar2(200) not null,
       cid number(5)
           constraint FK_stuInfo_cid references classInfo(cid),
       sex varchar2(4),
       photo varchar2(200),
       cardID varchar2(40),--身份证号
       tel varchar2(20),-- 联系方式
       status number(2)
)
--sql语句
drop table stuInfo
select * from stuInfo
delete ffrom stuInfo 

--题型表 
create table questionTypes(
        tid number(5) primary key,
        tname varchar2(200) not null,--题目类型，单选，多选，判断，填空
        status number(2)
)    



 --题型表序列
create sequence seq_questionTypes_tid start with 1; 
--sql语句
drop table questionTypes
select * from questionTypes
delete ffrom questionTypes
insert into  questionTypes values(seq_questionTypes_tid.nextval,'单选',1);
insert into  questionTypes values(seq_questionTypes_tid.nextval,'多选',1);
insert into  questionTypes values(seq_questionTypes_tid.nextval,'判断',1);
insert into  questionTypes values(seq_questionTypes_tid.nextval,'填空',1);

--题目表
create table questions(
       qid varchar2(200) primary key,
       qname varchar2(200) not null unique, --题目名称
       tid number(5) --题目类型
            constraint FK_questions_tid references questionTypes(tid),
       cid number(5) --课程类型
            constraint FK_questions_cid references courseInfo(cid), 
       ans1 varchar2(500),--选项A
       ans2 varchar2(500),--选项B
       ans3 varchar2(500),--选项C
       ans4 varchar2(500),--选项D
       ans varchar2(500),--正确答案
       status number(2)
)
 --题库表序列
create sequence seq_questions_qid start with 1;  
--创建触发器
create or replace trigger tri_questions_qid 
before insert on questions
for each row  --每影响一行触发一次
begin 
    select 'Q_'||seq_questions_qid.nextval into :new.qid from dual;
    end;
    
--sql语句
drop table questions
select * from questions
delete ffrom questions

--试卷信息表
create table   examPaper (
        eid number(10) primary key,
        ename varchar2(200) not null unique, --试卷名称
        conTime number(4),--考试时长
        examTime date,--开考时间
        cids varchar2(200),--考试班级 1,2,3,4
        subjects varchar2(4000), --题目信息 S_1-A-1,S_100-ABD-2,F_2-select-4，F_2-1-3 题目-正确答案，题号
        score varchar2(100),--每种题型的分数 1-2；2-4；3-1；4-2
        status number(2) --未考，开考，考试中，已阅
)
 --试卷信息表序列
create sequence seq_examPaper_eid start with 1; 
--sql语句
drop table examPaper
select * from examPaper
delete ffrom examPaper 
--答卷信息表 
create table ansPaper(
       aid number(10) primary key,
       pid number(10)
           constraint FK_ansPaper_pid references examPaper(eid),  --试卷编号
       sid varchar2(20) 
            constraint FK_ansPaper_sid references stuInfo(sid),--学生学号
       ans varchar2(4000) ,--答案   S_1-A-1,S_100-ABD-2,F_2-select-4，F_2-1-3
       score number(5,2), --分数
       surplus number(2),--剩余时长
       status number(2)
)
  --试卷信息表序列
create sequence seq_ansPaper_aid start with 1; 
--sql语句
drop table ansPaper
drop sequence seq_ansPaper_aid
select * from ansPaper
delete ffrom ansPaper        











