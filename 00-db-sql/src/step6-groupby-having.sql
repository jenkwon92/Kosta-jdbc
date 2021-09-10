/*
	group by - having
  	
 	group by : 테이블에서 특정컬럼을 기준으로 그룹화하여 검색할 떄 사용
  						데이터를 원하는 그룹으로 나눌 수 있다.
	having : group by 와 함께 사용하는 조건절(그룹에 대한 조건을 지정)
  
	예) 상품 테이블에서 제조사별 상품 수 , 평균가
  		사원 테이블에서 부서별 사원수, 평균월급
  
*/

SELECT * FROM product;

--maker별 상품수 조회
SELECT maker, COUNT(*) AS 상품수 FROM product GROUP BY maker ORDER BY 상품수 desc;

--maker별 상품수 조회하되 상품수가 1개를 초과하는 maker(그룹)들만 조회 (group by - having)
SELECT maker, COUNT(*) AS 상품수 
FROM product
GROUP BY maker 
HAVING COUNT(*)>1
ORDER BY 상품수 desc;

--maker 별 상품 평균가 
--avg()를 조회하되 평균가 내림차순으로 정렬 ( maker, 평균가 조회)
SELECT maker , ROUND(AVG(price)) as 평균가 FROM product GROUP BY maker ORDER BY 평균가 desc;

--maker별 상품 평균가(소수점 이하 반올림) 가 1540원을 초과하는 제조사 maker 를 조회하되
-- maker별 상품수, 평균가를 조회한다 (maker , 상품수, 평균가)
-- 상품수 오름차순으로 조회
SELECT maker, COUNT(*) AS 상품수, ROUND(AVG(price)) AS 평균가
FROM product
GROUP BY maker
HAVING ROUND(AVG(price)) >1540 
ORDER BY 상품수 ASC;

SELECT * FROM s_employee;

--job별 사원수 (job, 사원수) 를 조회하되 사원수 내림차순 정렬
SELECT job, COUNT(*) AS 사원수 FROM s_employee GROUP BY job ORDER BY 사원수 desc;

--job별 평균월급(avg(salary))을 조회하되 평균월급 내림차순 정렬 (job, 평균월급)
-- 평균월급이 700을 초과하는 JOB에 한해서 조회한다
SELECT job, AVG(salary)	AS 평균월급
FROM s_employee 
GROUP BY job 
HAVING AVG(salary) >700
ORDER BY 평균월급 DESC;

SELECT * FROM s_employee;

--job에 해당하는 사원의 가장 높은 salary 를 조회
SELECT MAX(salary) FROM s_employee WHERE job='개발'
--위 SQL에서 조회된 salary 를 받는 사원 정보
SELECT empno,name, job, salary FROM s_employee WHERE job='개발' AND salary =(SELECT MAX(salary) FROM s_employee WHERE job='개발');

 --sql test
 SELECT job, COUNT(*) AS 사원수 ,MAX(salary) AS 최고월급 FROM s_employee GROUP BY job ORDER BY 최고월급 DESC
 
 --전체 사원의 평균월급(730) 보다 job그룹별 평균월급액이 작은 job그룹의job, 평균월급, 사원수를 조회
 SELECT AVG(salary) FROM s_employee;
 SELECT AVG(salary) FROM s_employee WHERE job='영업'; --600
 SELECT AVG(salary) FROM s_employee WHERE job='개발'; --833.33
 SELECT AVG(salary) FROM s_employee WHERE job='총무';  --720
 
 SELECT AVG(salary) FROM s_employee GROUP BY job;
 
 --총무직종, 영업직종 조회결과로 나와야 한다
 SELECT job,AVG(salary) FROM s_employee GROUP BY job HAVING AVG(salary) >(SELECT AVG(salary) FROM s_employee );
 
 SELECT job,AVG(salary) AS avgsal FROM s_employee GROUP BY job HAVING AVG(salary) <(SELECT AVG(salary) FROM s_employee) ORDER BY avgsal DESC;

--product 
SELECT * FROM product;

SELECT AVG(price) FROM product

SELECT maker,ROUND(AVG(price)) AS avgprice 
FROM product 
GROUP BY maker 
HAVING ROUND(AVG(price))<(SELECT ROUND(AVG(price)) FROM product) 
ORDER BY avgprice DESC;

--- 제조사별 상품의 평균가가 전체 상품의 평균가보다 작은 제조사의 이름과 상품평균가를 조회하되 평균가 내림차순으로 정렬한다
SELECT maker, ROUND(AVG(price)) AS avgprice
FROM product
GROUP BY maker
HAVING ROUND(AVG(price))	<(SELECT ROUND(AVG(price)) FROM product)
ORDER BY avgprice DESC;






