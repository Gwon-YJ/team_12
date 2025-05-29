package com.example.newsfeed.comment.service;

import com.example.newsfeed.comment.entity.Comment;
import com.example.newsfeed.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    // 1. 속성
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // 2. 생성자
    public CommentService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    // 3. 기능
    // 댓글 생성
    public void addCommit(Long postId, String comment) {
        Post post = postRepository.fiedById(postId)
                .orElseThrow(() -> new RuntimeException("게시글이 없습니다."));
        Comment comment = new Comment();
        comment.setComment(comment);
        comment.setPost(post);

        commentRepository.save(comment);


        // 댓글 조회

        // 댓글 수정

        // 댓글 삭제
    }
}
