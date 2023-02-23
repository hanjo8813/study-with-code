use test;

-- MEMBER 회원
CREATE TABLE member
(
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    password  VARCHAR(12) NOT NULL, -- 비밀번호
    name      VARCHAR(10) NOT NULL  -- 이름
);

-- BOARD 게시판
CREATE TABLE board
(
    board_id  INT AUTO_INCREMENT PRIMARY KEY,     -- 게시글 ID
    member_id INT NOT NULL,                       -- 작성자 ID
    title     VARCHAR(100),                       -- 제목
    content   VARCHAR(1000),                      -- 내용
    ins_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 날짜
);

-- COMMENT 댓글
CREATE TABLE comment
(
    comment_id INT AUTO_INCREMENT PRIMARY KEY,     -- 댓글 ID
    board_id   INT NOT NULL,                       -- 게시글 ID
    member_id  INT NOT NULL,                       -- 작성자 ID
    content    VARCHAR(100),                       -- 댓글 내용
    ins_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 날짜
);

-- LIKE 좋아요
CREATE TABLE likes
(
    like_id   INT AUTO_INCREMENT PRIMARY KEY, -- 좋아요 ID
    board_id  INT NOT NULL,
    member_id INT NOT NULL,
    like_yn   BOOLEAN,
    ins_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO member (member_id, password, name)
VALUES (1, '123', '김'),
       (2, '123', '이'),
       (3, '123', '조'),
       (4, '123', '최'),
       (5, '123', '한');

INSERT INTO board (member_id, title, content)
VALUES (1, '테스트게시글', '첫번째 게시글입니다'),
       (2, '스터디', '여기는 webflux 스터디입니다');

INSERT INTO comment (board_id, member_id, content)
VALUES (1, 1, '안녕하세요'),
       (2, 5, '열심히 공부합시다');

INSERT INTO likes (board_id, member_id, like_yn)
VALUES (1, 3, true),
       (2, 3, true);