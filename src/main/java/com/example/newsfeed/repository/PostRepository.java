package com.example.newsfeed.repository;

import com.example.newsfeed.entity.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByPostId(Long userId);

    //Post Byid (Long id);

    // 주어진 시작일과 종료일 사이에 생성된 게시글 목록을 조회
    List<Post> findAllByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate, Sort sort);
}
