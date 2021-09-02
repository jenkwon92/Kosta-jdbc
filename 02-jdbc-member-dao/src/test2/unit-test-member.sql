-- member sql test
SELECT * FROM member;

--총 상품 수 조회
SELECT COUNT(*) FROM product;

--id에 대한 상품 존재 유무
SELECT COUNT(*) FROM product WHERE id=1; --존재하면 1
SELECT COUNT(*) FROM product WHERE id=7; --존재하지 않으면0