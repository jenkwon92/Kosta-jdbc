/* 

오라클 시퀀스(sequence)

: 순차적으로 증가, 유일한 값을 생성하기 위한 객체 
주로 primary key ( unique + not null ) 를 생성하기 위해 사용
테이블과는 독립적 구조 

​

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

SELECT * FROM member;











