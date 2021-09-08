CREATE TABLE account (
	account_no NUMBER PRIMARY KEY,
	name VARCHAR2(100) NOT NULL,
	password VARCHAR2(100) NOT NULL,
	balance NUMBER NOT NULL
)

CREATE SEQUENCE account_seq;

SELECT * FROM account;


--계좌 개설
INSERT INTO account(account_no, name, password, balance) VALUES(account_seq.NEXTVAL, '아이유', '1234',1000);

--잔액 조회 , account_no가 존재하지 않으면 조회결과가 나오지 않는다
SELECT password, balance FROM account WHERE account_no=1;