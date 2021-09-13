/*
 * oreign Key 제약조건과  Join SQL
 */

-- 테이블 생성 순서는 부모 테이블(참조 대상테이블) 부터 자식테이블(참조하는 테이블) 순으로 생성한다 
CREATE TABLE department(
	deptno NUMBER PRIMARY KEY,
	dname VARCHAR2(100) NOT NULL,
	loc VARCHAR2(100) NOT NULL,
	tel VARCHAR2(100) NOT NULL
)

CREATE TABLE k_employee(
	empno NUMBER PRIMARY KEY,
	ename VARCHAR2(100) NOT NULL,
	sal NUMBER NOT NULL,
	deptno NUMBER NOT NULL, 
	CONSTRAINT fk_deptno FOREIGN KEY (deptno) REFERENCES department(deptno)
)
-- FOREIGN KEY : CONSTRAINT 제약조건 명 FOREIGN KEY (현재테이블의 fk컬럼명) REFERENCES 참조테이블명(컬럼명)
-- 참조대상이 되는 테이블(부서 department) 을 부모테이블이라고 하고 참조하는 테이블(사원 k_employee) 을 자식테이블이라고 한다 

-- 만약 부모테이블인 부서테이블에 10번 부서번호가 없다면 아래 INSERT 구문은 error 가 난다 - parent key not found
-- foreign key 제약조건 위배 (참조 무결성 제약조건 위배)
INSERT INTO k_employee(empno,ename,sal,deptno) VALUES(1,'아이유',500,10);

INSERT INTO department(deptno,dname, loc,tel) VALUES (10,'전략기획','오리','031');
INSERT INTO department(deptno,dname, loc,tel) VALUES (20,'공공사업','종로','02');
INSERT INTO department(deptno,dname, loc,tel) VALUES (30,'연구개발','판교','032');
-- 부모 테이블에 10번 부서를 등록한 후 아래 사원정보를 등록하면 된다.
INSERT INTO k_employee(empno,ename,sal,deptno) VALUES(1,'아이유',500,10);
INSERT INTO k_employee(empno,ename,sal,deptno) VALUES(2,'박보검',700,10);
INSERT INTO k_employee(empno,ename,sal,deptno) VALUES(3,'강하늘',800,20);
-- 참조 무결선 위배( foreign key 제약 조건 위배) -- error
INSERT INTO k_employee(empno,ename,sal,deptno) VALUES(4,'김태리',800,40);

commit







