create table account(
	account_no number primary key,
	name varchar2(100) not null,
	password varchar2(100) not null,
	balance number not null
)
create sequence account_seq;

-- sql 단위 테스트 

select * from account;

-- 계좌 개설 
insert into account(account_no,name,password,balance) values(account_seq.nextval,'아이유','1234',1000);

-- 잔액조회 , account_no 가 존재하지 않으면 조회결과가 나오지 않는다 
select password,balance
from account
where account_no=7

-- 계좌번호 유무와 비밀번호 확인 
-- 계좌번호가 존재하면 패스워드가 조회
select password from account where account_no=1;  
-- 계좌번호가 존재하지 않으면 조회결과가 없다 
select password from account where account_no=7; 

-- 입금 
select * from account where account_no=1;

update account set balance=balance+50 where account_no=1;

































