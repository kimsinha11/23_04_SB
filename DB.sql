#DB 생성
DROP DATABASE IF EXISTS SB_AM_04;
CREATE DATABASE SB_AM_04;
USE SB_AM_04;

# 게시물 테이블 생성
CREATE TABLE article(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL,
    memberId INT(10) NOT NULL
);
#글 작성시 작성자 정보 남기려고 memberId 추가 >> ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER `body`;

# 게시물 테스트데이터 생성
INSERT INTO article 
SET regDate = NOW(),
updateDate = NOW(),
title = '제목 1',
`body` = '내용 1',
memberId = 2;

INSERT INTO article 
SET regDate = NOW(),
updateDate = NOW(),
title = '제목 2',
`body` = '내용 2',
memberId= 3;

INSERT INTO article 
SET regDate = NOW(),
updateDate = NOW(),
title = '제목 3',
`body` = '내용 3',
memberId= 2;


INSERT INTO article 
SET regDate = NOW(),
updateDate = NOW(),
title = '제목 3',
`body` = '내용 3',
memberId= 1;

INSERT INTO article 
SET regDate = NOW(),
updateDate = NOW(),
title = 'QnA',
`body` = '내용 3',
memberId= 3,
boardId = 3;


# 회원 테이블 생성
CREATE TABLE `member`(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(20) NOT NULL,
    loginPw CHAR(60) NOT NULL,
    `authLevel` SMALLINT(2) UNSIGNED DEFAULT 3 COMMENT '권한 레벨 (3=일반,7=관리자)',
    `name` CHAR(20) NOT NULL,
    nickname CHAR(20) NOT NULL,
    cellphoneNum CHAR(20) NOT NULL,
    email CHAR(50) NOT NULL,
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴 여부 (0=탈퇴 전, 1=탈퇴 후)',
    delDate DATETIME COMMENT '탈퇴 날짜'
);




# 회원 테스트데이터 생성 (관리자)
INSERT INTO `member` 
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
`authLevel` = 7,
`name` = '관리자',
`nickname` = '관리자',
cellphoneNum = '01012341234',
email = 'abcdef@gmail.com';

# 회원 테스트데이터 생성 (일반)
INSERT INTO `member` 
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test1',
loginPw = 'test1',
`name` = '회원1',
`nickname` = '회원1',
cellphoneNum = '01043214321',
email = 'abcd@gmail.com';

INSERT INTO `member` 
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test2',
loginPw = 'test2',
`name` = '회원2',
`nickname` = '회원2',
cellphoneNum = '01067896789',
email = 'zxcv@gmail.com';


# 게시물 테이블 생성
CREATE TABLE board(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `code` CHAR(50) NOT NULL UNIQUE COMMENT 'notice(공지사항), free(자유), QnA(질의응답), ....',
    `name` CHAR(50) NOT NULL UNIQUE COMMENT '게시판 이름',
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '삭제 여부 (0=삭제 전, 1=삭제 후)',
    delDate DATETIME COMMENT '삭제 날짜'
);

INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'notice',
`name` = '공지사항';

INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'free',
`name` = '자유';

INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'QnA',
`name` = '질의응답';


# 좋아요 테이블 생성
CREATE TABLE reactionPoint(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    relTypeCode CHAR(50) NOT NULL COMMENT '관련 데이터 타입 코드',
    relId INT(10) NOT NULL COMMENT '관련 데이터 번호',
    `point` INT(10) NOT NULL
);

#reactionPoint 테스트 데이터
#1번 회원이 1번 글에 싫어요
INSERT INTO reactionPoint
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
relTypeCode = 'article',
relId = 1, 
`point` = -1;

#1번 회원이 2번 글에 좋아요
INSERT INTO reactionPoint
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
relTypeCode = 'article',
relId = 2, 
`point` = 1;

#2번 회원이 1번 글에 싫어요
INSERT INTO reactionPoint
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
relTypeCode = 'article',
relId = 1, 
`point` = -1;

#2번 회원이 2번 글에 싫어요
INSERT INTO reactionPoint
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
relTypeCode = 'article',
relId = 2, 
`point` = -1;

#3번 회원이 1번 글에 좋아요
INSERT INTO reactionPoint
SET regDate = NOW(),
updateDate = NOW(),
memberId = 3,
relTypeCode = 'article',
relId = 1, 
`point` = 1;



# 게시물 갯수 늘리기
INSERT INTO article
(regDate, updateDate, memberId, boardId, title, `body`)
SELECT NOW(), NOW(), FLOOR(RAND() * 2) + 2,FLOOR(RAND() * 2) + 1, CONCAT('제목_',RAND()), CONCAT('내용_',RAND())
FROM article;
SELECT NOW()
SELECT FLOOR(RAND()*2) +2
SELECT RAND()
;

ALTER TABLE article ADD hitCount INT(10) UNSIGNED NOT NULL AFTER boardId;
SELECT*FROM `member` WHERE `name` LIKE "%회원%";

ALTER TABLE article ADD boardId INT(10) UNSIGNED NOT NULL AFTER `memberId`;

UPDATE article SET boardId = 1 WHERE id IN (1,2);

UPDATE article SET boardId = 2 WHERE id = 3;

UPDATE article SET boardId = 3 WHERE id = 4;
###################################################################################

SELECT * FROM article;
SELECT * FROM `member`;
SELECT * FROM board;
SELECT * FROM reactionPoint;
DESC `member`;


SELECT article.id, article.regDate, article.updateDate, article.title, article.body, article.memberId, article.boardId, board.id, member.name AS 'name'
FROM article
INNER JOIN `member`
ON article.memberId = member.id
INNER JOIN board
ON article.boardId = board.id
WHERE board.id = 3
ORDER BY article.id DESC


SELECT LAST_INSERT_ID();