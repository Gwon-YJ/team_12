CREATE TABLE users
(
    userId     BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '작성자 고유식별자',
    userName   VARCHAR(20)        NOT NULL COMMENT '이름',
    customId   VARCHAR(255)       NOT NULL UNIQUE,
    password   VARCHAR(30)        NOT NULL COMMENT '비밀번호',
    email      VARCHAR(50) UNIQUE NOT NULL COMMENT '이메일',
    role       VARCHAR(255)       NOT NULL COMMENT '역할',
    createdAt  DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
    modifiedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
);

CREATE TABLE posts
(
    postId        BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '게시글 고유식별자',
    title         VARCHAR(255) NOT NULL COMMENT '제목',
    content       VARCHAR(255) NOT NULL COMMENT '내용',
    likesCount    BIGINT   DEFAULT 0 COMMENT '좋아요 갯수',
    commentsCount BIGINT   DEFAULT 0 COMMENT '댓글 갯수',
    userId        BIGINT COMMENT '유저 id',
    createdAt     DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
    modifiedAt    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    CONSTRAINT fk_user FOREIGN KEY (userId) REFERENCES users (userId)
);

CREATE TABLE likes
(
    likesId    BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '좋아요 고유식별자',
    likeCount  BIGINT NOT NULL COMMENT '좋아요 갯수',
    postId     BIGINT COMMENT '게시글 id',
    userId     BIGINT COMMENT '유저 id',
    createdAt  DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
    modifiedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    CONSTRAINT fk_likes_post FOREIGN KEY (postId) REFERENCES posts (postId),
    CONSTRAINT fk_likes_user FOREIGN KEY (userId) REFERENCES users (userId)
);

CREATE TABLE follows
(
    followId    BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '팔로우 고유식별자',
    followerId  BIGINT NOT NULL COMMENT '팔로워 id',
    followingId BIGINT NOT NULL COMMENT '팔로잉 id',
    createdAt   DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
    modifiedAt  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    CONSTRAINT fk_follows_follower FOREIGN KEY (followerId) REFERENCES users (userId),
    CONSTRAINT fk_follows_following FOREIGN KEY (followingId) REFERENCES users (userId)
);

CREATE TABLE comments
(
    commentId     BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '댓글 고유식별자',
    comment       VARCHAR(255) NOT NULL COMMENT '댓글',
    commentsCount BIGINT       NOT NULL COMMENT '댓글 갯수',
    postId        BIGINT COMMENT '게시글 id',
    userId        BIGINT COMMENT '유저 id',
    createdAt     DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
    modifiedAt    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    CONSTRAINT fk_comments_post FOREIGN KEY (postId) REFERENCES posts (postId),
    CONSTRAINT fk_comments_user FOREIGN KEY (userId) REFERENCES users (userId)
);