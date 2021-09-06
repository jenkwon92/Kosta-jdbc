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


/*
 * 테이블 명 : product
 * 컬럼 명 : id, name, maker, price
 * 데이터 타입  : id, price 는 NUMBER
 * 						name, maker 는 NOT NULL
 * 	제약 조건 : id는 PRIMARY KEY
 * 						name, maker 는 NOT NULL
 * 						price는 DEFAULT 0
 */

-- DDL
CREATE TABLE product(
	id NUMBER PRIMARY KEY,
	name VARCHAR2(100) NOT NULL,
	maker VARCHAR2(100) NOT NULL,
	price NUMBER DEFAULT 0
)

--DML : INSERT
INSERT INTO product VALUES(1,'불닭볶음면','삼양',1500);
INSERT INTO product VALUES(2,'진라면','오뚜기',1100);
INSERT INTO product VALUES(4,'소주','하이트진로',1600);
INSERT INTO product VALUES(3,'테라','하이트진로',1800);

--DCL
COMMIT

--DML : SELECT
SELECT * FROM product;

-- 데이터 베이스 함수 테스트
-- COUNT(*)
SELECT COUNT(*) FROM product; --총 상품수
SELECT COUNT(*) FROM member; -- 총 회원수

-- 상품 최저가 
SELECT MIN(price) FROM product;
--상품 최고가 
SELECT MAX(price) FROM product;
--상품 평균가 
SELECT AVG(price) FROM product;
-- 상품 평균가 반올림
SELECT ROUND(AVG(price)) FROM product;

-- 정렬 : 오름차순, 내림차순 정렬
-- price 오름차순 정렬
-- ORDER BY 만 명시하면 기본 오름차순
SELECT name, price FROM product ORDER BY price;
SELECT name, price FROM product ORDER BY price ASC;

--price 내림차순 정렬
SELECT name, price FROM product ORDER BY price DESC;

/*
 * SELECT 
 * FROM 
 * WHERE
 * ORDER BY
 */
SELECT id,name,price 
FROM product 
WHERE maker='하이트진로' 
ORDER BY price DESC;

SELECT * FROM product;

--price가 1300 이상 , 2000 이하 인 상품의 name과 price를 조회
SELECT name, price 
FROM product 
WHERE price >= 1300 AND price <=2000 
ORDER BY price ASC;

-- BETWEEN AND 로 위의 SQL을 표현할 수 있다
SELECT name, price 
FROM product
WHERE price BETWEEN 1300 AND 2000
ORDER BY price ASC;

-- SUBQUERY
 -- 서브쿼리란 부모 쿼리 안에 작성하는 내부의 SELECT 쿼리. 주로 부모 쿼리의 FROM과 WHERE의 조건으로 사용.
 -- SELECT를 괄호( )로 묶어서 가독성을 높인다
 
 SELECT MAX(PRICE) FROM PRODUCT;
 SELECT NAME FROM PRODUCT WHERE PRICE=1800;
 -- 위 두 SQL을 합친 것이 SUBQUERY
 SELECT NAME FROM PRODUCT WHERE PRICE=(SELECT MAX(PRICE) FROM PRODUCT); --**************


--PRIMARY KEY로 검색해서 상품 존재 유무 확인
SELECT COUNT(*) FROM product WHERE id=1; --존재하면 1
SELECT COUNT(*) FROM product WHERE id=7; --존재하지 않으면 0

--컬럼 별칭
SELECT MIN(price) AS 최저가 FROM product;

-- maker 조회 (중복된 maker정보까지 모두 조회)
SELECT maker FROM product;

--maker 종류를 조회  (중복없이 maker 종류만 조회)
SELECT DISTINCT maker FROM product;

-- LIKE 연산자 : 문자열의 일부가 포함되는 내용을 검색하는 연산자 (웹의 검색 기능에서 많이 사용됨)
--WHERE 컬럼명 LIKE '%검색키워드%'
-- % 0개 이상의 문자
SELECT * FROM product WHERE name LIKE '%면%'; 
SELECT * FROM product WHERE maker LIKE '%트진%'; 

CREATE SEQUENCE guestbook_seq;
SELECT * FROM guestbook;

--LIKE 연산자를 이용해 title에 즐 문자열이 포함된 방명록 정보를 조회
SELECT * FROM guestbook WHERE title LIKE '%즐%';

-- 위 LIKE 적용 SQL PreparedStatement 에 적용하는 방법은 아래와 같다
/*
 	String sql ="SELECT * FROM guestbook WHERE title LIKE '%' || ? || '%' ";
 */























