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












