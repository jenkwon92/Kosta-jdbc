--사원 테이블
CREATE TABLE s_employee(
	empno 	NUMBER PRIMARY KEY,
	name VARCHAR2(100) NOT NULL,
	job VARCHAR2(100) NOT NULL,
	salary NUMBER NOT NULL
)

--시퀀스
CREATE sequence s_employee_seq;
 
INSERT INTO s_employee(empno, name, job, salary) VALUES (s_employee_seq.NEXTVAL, '아이유','개발',700);

INSERT INTO s_employee(empno, name, job, salary) VALUES (s_employee_seq.NEXTVAL, '강하늘','개발',900);
INSERT INTO s_employee(empno, name, job, salary) VALUES (s_employee_seq.NEXTVAL, '유재석','총무',600);
INSERT INTO s_employee(empno, name, job, salary) VALUES (s_employee_seq.NEXTVAL, '박보검','개발',900);
INSERT INTO s_employee(empno, name, job, salary) VALUES (s_employee_seq.NEXTVAL, '이상순','총무',600);
 
SELECT * FROM s_employee;

--개발 직종 사원중 가장 높은 salary를 받는 사원 정보
SELECT * FROM s_employee WHERE salary = (SELECT MAX(salary) FROM s_employee) AND job='개발'; 

SELECT * FROM s_employee WHERE salary = (SELECT MAX(salary) FROM s_employee WHERE job='개발');

--job에 해당하는 사원의 가장 높은 salary 를 조회
SELECT MAX(salary) FROM s_employee WHERE job='개발'
--위 SQL에서 조회된 salary 를 받는 사원 정보
SELECT empno,name, job, salary FROM s_employee WHERE job='개발' AND salary =(SELECT MAX(salary) FROM s_employee WHERE job='개발');

 