/*
 	SQL : 데이터베이스를 정의 조작 제어하는 언어
 	1) DDL 데이터 정의어 -  CREATE, DROP, ALTER
 	2) DML 데이터 조작어 - INERT, SELECT, UPDATE, DELETE(CRUD)
 	3) DCL 데이터 제어어 -  COMMIT, ROLLBACK
 	
 */
--DATA TYPE : 문자열 - 오라클에서 권장하는 문자열 타입 VARCHAR2(10)
--						  정수, 실수 - NUMBER

-- DDL : CREATE 테이블 생성
CREATE TABLE typetest(
	name VARCHAR2(9) PRIMARY KEY,
	money NUMBER NOT NULL
)

-- DML : INSERT 
-- ERROR  : NAME의 최대사이즈가 9 , 아래는 10개 이므로 ERROR
--value too large for column
INSERT INTO typetest(name, money) VALUES ('abcdefghij',100);
INSERT INTO typetest(name, money) VALUES ('abcdefghi',100);
--한글은 3byte를 차지, 아래는 12byte 이므로 error
INSERT INTO typetest(name, money) VALUES ('아이유님',100);
INSERT INTO typetest(name, money) VALUES ('아이유',100);

-- DML : SELECT
SELECT name, money FROM typetest;

-- DML : UPDATE
-- name이 아이유인 대상의 money를 +200해본다
UPDATE typetest SET money = money+200 WHERE name='아이유'; 

-- DDL : DROP - 테이블 삭제 ( table or view does not exist)
DROP TABLE typetest;

-- DDL : ALTER - 테이블 정보를 변경 (TABLE 명 변경, column명 변경, 제약조건 및 타입 변경)
CREATE TABLE altertest(
	id VARCHAR2(100) PRIMARY KEY,
	hit NUMBER DEFAULT 0
) 
--DEFAULT 제약조건 : 별도로 INSERT 하지 않으면 0으로 초기값이 저장
-- DML : INSERT , hit는 0으로 저장된다
INSERT INTO altertest (id) VALUES ('java');
SELECT id,hit FROM altertest;

-- DDL : ALTER를 이용해서 테이블명을 altertest2로 변경해본다
ALTER TABLE altertest RENAME TO altertest2;
SELECT * FROM altertest2;
-- DDL : ALTER를 이용해서 컬럼명을 hit에서 count로 변경해본다 
ALTER TABLE altertest2 RENAME COLUMN hit TO count;

SELECT * FROM altertest2;


















