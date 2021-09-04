--guestbook TABLE 에 SEQUENCE를 이용
CREATE TABLE guestbook(
	guestbook_no NUMBER PRIMARY KEY,
	title VARCHAR2(100) NOT NULL,
	content VARCHAR2(1000) NOT NULL
)

CREATE SEQUENCE guestbook_seq;
SELECT * FROM guestbook;

ALTER TABLE guestbook 	RENAME COLUMN questbook_no TO guestbook_no;

