CREATE DATABASE HFC;

-- 유저 테이블 생성
CREATE TABLE MEMBER(
	ID INT(4) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	EMAIL VARCHAR(30) NOT NULL,
	PASSWORD VARCHAR(255) NOT NULL,
	NICKNAME VARCHAR(30) NOT NULL,
	GRADE VARCHAR(10)DEFAULT 'BASIC',                   -- 회원등급
	PROFILE LONGTEXT DEFAULT '/image/non-profile.png',  -- 프로필 이미지
	VISITED_DATE INT(4) DEFAULT 0,                      -- 방문횟수
	CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP      -- 가입일
);
-- 메인 카테고리 테이블 생성
CREATE TABLE MAIN_CTGRY(
	ID INT(2) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	NAME VARCHAR(30) NOT NULL                           -- 메인 카테고리명
);
-- 서브 카테고리 테이블 생성
CREATE TABLE SUB_CTGRY(
	ID INT(2) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	NAME VARCHAR(30) NOT NULL,                          -- 서브 카테고리명
	PARENT INT(2) NOT NULL,                             -- 메인 카테고리의 PK
	FOREIGN KEY(PARENT) REFERENCES MAIN_CTGRY(ID)
);
-- 게시글 테이블 생성
CREATE TABLE POST (
    ID INT(4) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    TITLE VARCHAR(60) NOT NULL,
    CONTENT LONGTEXT NOT NULL,
    PREPEND VARCHAR(10),                                -- 관리자의 경우 게시글에 말머리 사용 가능
    HIT INT(4) DEFAULT 0,                               -- 조회수
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,     -- 생성일
    PUBLIC_OPTION CHAR(1) DEFAULT 'Y',                  -- 공개 옵션
    COMMENT_OPTION CHAR(1) DEFAULT 'Y',                 -- 댓글 허용 옵션
    CTGRY INT(2) NOT NULL,                              -- 카테고리
    WRITER INT(4) NOT NULL,                             -- 게시글 작성자
    FOREIGN KEY (SUB_CTGRY) REFERENCES SUB_CTGRY(ID),
    FOREIGN KEY (WRITER) REFERENCES MEMBER(ID)
);
-- 댓글 테이블 생성
CREATE TABLE COMMENT(
	ID INT(2) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	CONTENT LONGTEXT NOT NULL,
	CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,     -- 생성일
	WRITER INT(2) NOT NULL,                             -- 작성자의 PK
	POST INT(2) NOT NULL,                               -- 게시글의 PK
	FOREIGN KEY(WRITER) REFERENCES MEMBER(ID),
	FOREIGN KEY(POST) REFERENCES POST(ID)
)
-- 좋아요 테이블 생성
CREATE TABLE LIKED(
	ID INT(2) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	POST INT(2) NOT NULL,                               -- 게시글의 PK
	MEMBER INT(2) NOT NULL,                             -- 작성자의 PK
	FOREIGN KEY(POST) REFERENCES POST(ID),
	FOREIGN KEY(MEMBER) REFERENCES MEMBER(ID)
)



-- 회원 데이터 생성
INSERT INTO MEMBER
(EMAIL, PASSWORD, NICKNAME, GRADE)
values("rkdans113@naver.com", "Zkfps135!", "whiteDwarff", "O");

INSERT INTO MEMBER
(EMAIL, PASSWORD, NICKNAME)
VALUES
("winter_w@naver.com", sha2("Zkfps135!", 256), "winter_w"), ("kingsinyoung@naver.com", SHA2("Xptmxm12!", 256), "김신영"), ("SeungJin051@gmail.com", SHA2("Xptmxm12!", 256), "SeungJin051"), ("moneycloudy@naver.com", SHA2("Zkfps135!", 256), "서정호"), ("Hongseohyun@gmail.com", SHA2("Ghdtjgus12!", 256), "Seohyun Hong");
-- 메인 카테고리 데이터 생성
INSERT INTO MAIN_CTGRY
(NAME) VALUES("NOTICE"), ("COMMUNITY"), ("FASHION"), ("EVENT");
-- 서브 카테고리 데이터 생성
INSERT INTO SUB_CTGRY
(NAME, PARENT)
VALUES
("라이프", 4), ("패션", 4), ("OOTD", 4), ("쇼핑후기", 4), ("패션정보", 5), ("발매정보", 5),
("세일정보", 5), ("프로모션 모집", 6), ("프로모션 발표", 6), ("프로모션 후기", 6);