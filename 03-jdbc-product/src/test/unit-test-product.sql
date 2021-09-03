-- SELECT
SELECT * FROM product;

SELECT COUNT(*) FROM product;

--id에 대한 상품 존제 유무
SELECT COUNT(*) FROM product WHERE id = 1;
SELECT COUNT(*) FROM product WHERE id = 8;

--maker 종류 조회
SELECT DISTINCT maker FROM product;

-- BTWEEN - AND -
SELECT * 
FROM product 
WHERE price BETWEEN 1300 AND 1800 
ORDER BY price DESC;

--lowPrice, highPrice
SELECT id,name,maker,price 
FROM product
WHERE price>=1300 AND price<=1700
ORDER BY price DESC;



