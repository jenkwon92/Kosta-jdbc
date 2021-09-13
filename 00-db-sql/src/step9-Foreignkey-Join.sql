/*
 	 Foreign Key 제약조건과  Join SQL
 	
 	-ERD (Entity Relationship Diagram) : 관계형 데이터베이스 설계를 위한 다이어그램
 	
 	- 정규화 (Normalization) : 데이터베이스 설계시 중복을 최소화하고 무결성을 보장하기 위해 데이터를 구조화하는 작업
 												( 1. 2, 3 정규화) -> 이후 공부예정 
 												
 	- Foreign key 제약 조건 : 참조 무결성 보장을 위한 제약조건, 다른 테이블의 정보를 참조할 때 지정해야 하는 제약조건											
 											ex ) 부모 테이블 :  부서
 													자식 테이블 : 사원의 컬럼 부서번호에 FK 제약조건을 지정,  부모인 부서테이블의 PK를 참조
 													
 													사원 정보가 사원테이블에 저장되기 위해서는 반드시 부서테이블에 저장되어있는 부서번호로만 
 													사원의 부서번호 정보로 저장될 수 있다.
 													부서테이블에 존재하지 않는 부서번호로 사원테이블에 사원 정보의 부서번호로 저장될 수 없다
 	
 	- Join SQL : 여러 테이블 간의 정보를 결합하기 위한 SQL (기본적인 Join SQL 연습하고 상세한 것은 이후 공부예정)
 											
 																															
 	사원번호, 사원명, 직종 월급, 부서명,부서근무지,부서대표전화번호.........
 	
 	실제 데이터
 	1			아이유			개발직			500			연구개발			판교			031
 	2			박보검			개발직			700			광고사업부		종로			02
 	
 	위와 같은 형태로 테이블을 설계해서 운영하면 부서정보들이 중복, 반복적으로 저장
 	부서정보가 업데이트 되어야 한다면 사원수만큼의 다수의 데이터가 변경되어야 한다				
 	정규화를 거치면 아래와 같이 테이블을 분리하여 관계성(1 : 多)을 가지면 된다							
 												
 	부서 테이블 |---------------0|<-  사원테이블
 	
 	부서는 사원을 0 or 1 or 다수를 가질 수 있다
 	사원은 반드시 하나의 부서에 속해 있다.
 	
 	이렇게 테이블을 분리하여 관리할 때는 참조 무결성 보장을 위해 제약조건 FOREIGN KEY를 지정해야 한다
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

/*
 *  Join Sql
 	- 사원번호 1인 아이유 사원의 sal과 부서번호, 부서명, 지역을 조회하고자 한다
 		사원테이블과 부서테이블의 정보를 함께 조회해야 함 -> Join SQL을 이용하면 된다 
 		
 	형식 1
 	SELECT 컬럼명, 컬럼명
 	FROM 테이블명 별칭, 테이블명 별칭
 	WHERE 별칭.컬럼명=별칭.컬럼명 -- 조인 조건
 	
 	형식 2
 	SELECT 컬럼명, 컬럼명
 	FROM 테이블명 별칭
 	INNER JOIN 테이블명 별칭 ON  별칭.컬럼명= 별칭.컬럼명
 */
SELECT e.ename, e.sal, d.deptno,d.dname, d.loc
FROM k_employee e, department d
WHERE e.deptno = d.deptno
AND e.empno=1
-- 아래와 같이 표현할 수도 있다
SELECT e.ename, e.sal, d.deptno,d.dname, d.loc
FROM k_employee e
INNER JOIN department d ON e.deptno=d.deptno
WHERE e.empno=1

SELECT e.empno, e.ename, e.sal, d.deptno, d.dname, d.loc,d.tel
FROM k_employee e, department d
WHERE e.deptno = d.deptno
AND e.empno=1

SELECT e.empno, e.ename, e.sal, d.deptno, d.dname, d.loc, d.tel
FROM k_employee e
INNER JOIN department d ON e.deptno=d.deptno
WHERE e.empno=1

