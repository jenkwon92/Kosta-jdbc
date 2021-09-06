/* 
오라클 시퀀스(sequence)

: 순차적으로 증가, 유일한 값을 생성하기 위한 객체 
주로 primary key ( unique + not null ) 를 생성하기 위해 사용
테이블과는 독립적 구조 

실제 적용시 시퀀스명.NEXTVAL	 :다음 시퀀스 값이 반환

ex)실제 DAO 적용사례 
	INSERT INTO guestbook(guestbook_no, title) VALUES(guestbook_seq.nextval, ?);
	
현재 시퀀스 값을 반환 -> 시퀀스명 .currval
단 currval은 시퀀스 를 nextval 한 세션 내에서만 사용가능
(참고 -  session : database에 사용자가 접속~ 접속종료시까지 유지되는 정보)


CREATE SEQUENCE 시퀀스명 
[START WITH 시작번호]
[INCREMENT BY 증가값]
[MAXVALUE 최대값] 
[MINVALUE 최소값]
[ CYCLE or NOCYCLE ] 
[ NOCACHE ] 

Oracle dual table 

: 오라클에서 제공하는 기본 테이블 
컬럼 하나만 존재 , 주로 시퀀스 또는 날짜함수, 산술연산에 사용
sys Admin 계정에서 관리하고 수정 및 삭제 등 조작은 불가 

*/

-- 시퀀스 생성 : 시퀀스명 test_seq
CREATE SEQUENCE test_seq;

--시퀀스 삭제 
DROP SEQUENCE test_seq;

-- 오라클에서 제공하는 dual 테이블을 조회
SELECT * FROM DUAL;
--연산시 사용
SELECT 2*5 FROM DUAL;
-- 현재시간 SYSDATE
SELECT SYSDATE FROM DUAL;
--DUAL TABLE을 이용해 시퀀스 값을 확인
--시퀀스명.NEXTVAL - >시퀀스 다음 값을 생성
SELECT test_seq.NEXTVAL FROM DUAL;

--DROP한 test_seq 시퀀스명으로 다시 시퀀스를 생성해본다 (옵션을 start with 7 적용해서 생성)
CREATE SEQUENCE test_seq START WITH 7;

--Car TABLE을 생성해서 SEQUENCE 를 이용해본다 
CREATE TABLE Car (
	car_no NUMBER PRIMARY KEY,
	model VARCHAR2(100) NOT NULL
)
-- car_seq sequence 를 생성한다
CREATE SEQUENCE car_seq;
--INSERT 시에 SEQUENCE를 이용해 car_no를 등록한다
INSERT INTO car(car_no, model) VALUES(car_seq.NEXTVAL, '테슬라');
INSERT INTO car(car_no, model) VALUES(car_seq.NEXTVAL, '람보르기니');

SELECT * FROM car;

--guestbook TABLE 에 SEQUENCE를 이용
CREATE TABLE guestbook(
	guestbook_no NUMBER PRIMARY KEY,
	title VARCHAR2(100) NOT NULL,
	content VARCHAR2(1000) NOT NULL
)

CREATE SEQUENCE guestbook_seq;

-- 시퀀스명.NEXTVAL
SELECT guestbook_seq.NEXTVAL FROM DUAL;
--시퀀스명.CURRVAL : 단독으로 사용될 수 없고, NEXTVAL 한 컨넥션 내에서 CURRVAL 을 사용할 수 있다 (동일한 세션 내에서만 사용 가능)
SELECT guestbook_seq.CURRVAL FROM DUAL;





