create table account(
	account_no number primary key,
	name varchar2(100) not null,
	password varchar2(100) not null,
	balance number not null
)
create sequence account_seq;

-- sql 단위 테스트 

SELECT * FROM account;

--계좌 개설
INSERT INTO account(account_no, name, password, balance) VALUES(account_seq.NEXTVAL, '아이유', '1234',1000);

--잔액 조회 , account_no가 존재하지 않으면 조회결과가 나오지 않는다
SELECT password, balance FROM account WHERE account_no=1;

--계과번호 유무와 비밀번호 확인
-- 계좌번호가 존재하면 패스워드가 조회
SELECT password FROM account WHERE account_no=1;
--계좌번호가 존재하지 않으면 조회결과가 없다
SELECT password FROM account WHERE account_no=10;


--입금
SELECT * FROM account WHERE account_no=1;
UPDATE account SET balance=balance+50 WHERE account_no=1;















