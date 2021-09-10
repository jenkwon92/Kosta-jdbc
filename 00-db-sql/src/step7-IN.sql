/*

      IN 연산자
      특정값이 포함되는 데이터를 조회하고자 할 때 사용하는 연산자
      
      select 컬럼, 컬럼
      from 테이블명
      where 컬럼 in('또래오래','대한곱창');
      
      NOT IN 연산자 
      특정 값이 포함되지 않는 데이터를 조회하고자 할 때 
      
      또래오래와 대한곱창 제조사가 아닌 음식정보를 조회
      
      select 컬럼, 컬럼
      from 테이블명
      where 컬럼 not in('또래오래','대한곱창');

*/
--maker가 대한곱창, 이춘복참치 인 food 정보를 조회
SELECT id,name, maker,price
FROM food
WHERE maker  IN('대한곱창','이춘복참치');

--위 sql을 아래와 값이 표현할 수 있다.
SELECT id,name, maker,price
FROM food
WHERE maker='대한곱창' OR maker='이춘복참치';

--maker가 대한곱창, 이춘복참치 이 아닌 food 정보를 조회
SELECT id,name, maker,price
FROM food
WHERE maker  NOT IN('대한곱창','이춘복참치');

--위 sql을 아래와 값이 표현할 수 있다.
select id, name, maker, price
from FOOD
where maker<>'대한곱창' AND maker<> '이춘복참치';

SELECT id,name, maker,price
FROM food
WHERE maker!='대한곱창' AND maker!='이춘복참치';



CREATE TABLE food(
	id NUMBER PRIMARY KEY ,
	name VARCHAR2(100) NOT NULL,
	maker VARCHAR2(100) NOT NULL,
	price NUMBER NOT NULL
)

CREATE SEQUENCE food_seq;

INSERT INTO food(id,name,maker,price) VALUES (food_seq.NEXTVAL,'후라이드','또래오래',15000);
INSERT INTO food(id,name,maker,price) VALUES (food_seq.NEXTVAL,'소곱창','대한곱창',20000);
INSERT INTO food(id,name,maker,price) VALUES (food_seq.NEXTVAL,'양념치킨','또래오래',16000);
INSERT INTO food(id,name,maker,price) VALUES (food_seq.NEXTVAL,'참치회','이춘복참치',35000);
INSERT INTO food(id,name,maker,price) VALUES (food_seq.NEXTVAL,'파닭','또래오래',17000);
INSERT INTO food(id,name,maker,price) VALUES (food_seq.NEXTVAL,'미니전골','대한곱창',18000);

commit



SELECT COUNT(*) FROM food;
--평균가 20167
SELECT ROUND(AVG(price)) FROM food;

SELECT maker, ROUND(AVG(price)) AS avgprice 
FROM food 
GROUP BY maker 
ORDER BY avgprice DESC;

--전체 음식의 평균가보다 maker별 음식 평균가가 낮은 maker의 음식정보(name, price, maker) 를 조회하고자 한다
-- GROUP BY, HAVING , SUBQUERY, IN, ORDER BY 를 이용
SELECT ROUND(AVG(price)) FROM food;

--step 1. 전체 음식 평균가 
SELECT ROUND(AVG(price)) FROM food;
--step 2. maker별 음식 평균가 
SELECT maker, ROUND(AVG(price)) FROM food GROUP BY maker;
--step 3. 전체 음식의 평균가보다 maker 별 음식 평균가가 낮은 maker의 음식정보를 조회
SELECT maker 
FROM food 
GROUP BY maker 
HAVING ROUND(AVG(price)) < (SELECT ROUND(AVG(price)) FROM food);
--step 4. 전체 음식의 평균가보다 maker별 음식 평균가가 낮은 maker의 음식정보(name, price, maker) 를 조회하고자 한다
SELECT name, price,maker
FROM food
WHERE maker IN (
	SELECT maker 
	FROM food 
	GROUP BY maker 
	HAVING ROUND(AVG(price)) < (SELECT ROUND(AVG(price)) FROM food)
)
ORDER BY price DESC;
------------------------------------------------------------------------------

SELECT * FROM s_employee;
--job별 사원수가 3명 이상인 job에 해당하는 사원의 name과 job을 조회
--JOB이 개발, 총무 
SELECT name, job
FROM s_employee
WHERE job IN(
	SELECT job FROM s_employee GROUP BY job HAVING COUNT(*)>=3
)

/*
s_employee 테이블에 저장된 전체 사원 정보의 평균월급 (salary) 보다 
직종별(job) 평균월급이 낮은 직종(job)에 해당하는 사원 정보를 조회
*/
--전체 평균
SELECT AVG(salary) FROM s_employee;  --730

--직종의 평균 월급
SELECT AVG(salary) FROM s_employee WHERE job='개발';
SELECT AVG(salary) FROM s_employee WHERE job='총무';
SELECT AVG(salary) FROM s_employee WHERE job='영업';

SELECT job FROM s_employee GROUP BY job HAVING AVG(salary)<=(SELECT AVG(salary) FROM s_employee);

SELECT empno, name,job,salary
FROM s_employee
WHERE job IN( SELECT job FROM s_employee GROUP BY job HAVING AVG(salary)<=(SELECT AVG(salary) FROM s_employee))
ORDER BY salary DESC;

