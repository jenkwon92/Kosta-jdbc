/*
	SubQuery : 서브쿼리 , 부쿼리,  하위쿼리
						SQL내의 SQL 
*/
 SELECT * FROM product;

 -- 상품 정보 중 가장 비싼 가격의 상품명은?
 --1) 상품 최고가를 조회, 2200
 SELECT MAX(price) FROM product;
 --2) 최고가인 2200 price를 가진 상품명을 조회
 SELECT name FROM product WHERE price = 2200;
 
 -- 위의 1) 2) sql을 subquery 를 이용해 한번에 조회
 SELECT name FROM product WHERE price=(SELECT MAX(price) FROM product);
 
 -- 전체 상품의 평균가격보다 높은 가격의 상품들 중 가장 낮은 가격의 name, maker, price를 조회
--1 ) 전체 상품 평균가 :1600
 SELECT AVG(price) FROM product;
 --2) 상품 평균가보다 높은 가격의 상품들 중 가장 낮은 가격은 ? 1700
  SELECT	 MIN(price) FROM product WHERE price > 1600;
  --3) 상품 평균가보다 높은 가격의 상품들 중 가장 낮은 가격의 상품 정보를 조회
  SELECT name, maker, price FROM product WHERE price = 1700; 
  
  --1,2 번 sql을 subquery로 표현
 SELECT MIN(price) FROM product WHERE price>(SELECT AVG(price) FROM product);
  --1,2,3 을 subquery를 이용해 하나의 sql로 표현 
  SELECT name, maker, price FROM product WHERE price=(
  	SELECT MIN(price) FROM product WHERE price> (SELECT AVG(price) FROM product)
  )
  
 --연습) product 중 가장 낮은 price의 상품의 maker는? 오뚜기
 SELECT maker FROM product WHERE	price = (SELECT MIN(price) FROM product);
 
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
 
