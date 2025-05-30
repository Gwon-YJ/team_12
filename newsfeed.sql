CREATE TABLE user(
    id BIGINT AUTO_INCREMENT
        PRIMARY KEY
        COMMENT '작성자 고유식별자',
    userName VARCHAR(20)
       NOT NULL
       COMMENT '이름',
    email VARCHAR(50)
       UNIQUE
       NOT NULL
       COMMENT '이메일',
    password VARCHAR(30)
       NOT NULL
       COMMENT '비밀번호',
    postDate TIMESTAMP
       DEFAULT CURRENT_TIMESTAMP
       COMMENT '등록일',
    updateDate TIMESTAMP
       DEFAULT CURRENT_TIMESTAMP
       ON UPDATE CURRENT_TIMESTAMP
    COMMENT '수정일'
);