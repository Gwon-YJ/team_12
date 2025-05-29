package com.example.newsfeed.comment.service;

import com.example.newsfeed.comment.entity.Comment;
import com.example.newsfeed.comment.repository.CommentRepository;
import com.example.newsfeed.post.entity.Post;
import com.example.newsfeed.post.repository.PostRepository;
import com.example.newsfeed.session.entity.Session;
import com.example.newsfeed.session.repository.SessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final SessionRepository sessionRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentService(SessionRepository sessionRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.sessionRepository = sessionRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    // 댓글 생성
    public void addComment(Long sessionId, Long postId, String content) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("세션이 없습니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다."));

        Comment commentEntity = new Comment();
        commentEntity.setSession(session);
        commentEntity.setPost(post);
        commentEntity.setContent(content); // 필드명이 comment가 아니라 content일 가능성 큼

        commentRepository.save(commentEntity);
    }

    // 댓글 조회
    public List<Comment> findCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    // 댓글 수정
    public Comment updateComment(Long commentId, String newContent) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글이 없습니다."));
        comment.setContent(newContent);
        return commentRepository.save(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}

