package com.example.newsfeed.post.repository;

import com.example.newsfeed.post.entity.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);

    // 주어진 시작일과 종료일 사이에 생성된 게시글 목록을 조회
    List<Post> findAllByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate, Sort sort);
}
