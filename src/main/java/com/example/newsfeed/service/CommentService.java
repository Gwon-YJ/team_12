package com.example.newsfeed.service;

import com.example.newsfeed.dto.CommentResponseDto;
import com.example.newsfeed.entity.Comment;
import com.example.newsfeed.repository.CommentRepository;
import com.example.newsfeed.post.entity.Post;
import com.example.newsfeed.post.repository.PostRepository;
import com.example.newsfeed.session.entity.Session;
import com.example.newsfeed.session.repository.SessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public void saveComment(Long sessionId, Long postId, String comment) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("세션이 없습니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다."));

        Comment commentEntity = new Comment();
        commentEntity.setSession(session);
        commentEntity.setPost(post);
        commentEntity.setComment(comment);

        commentRepository.save(commentEntity);
    }

    // 댓글 조회
    public List<CommentResponseDto> findComment(Long postId) {
        List<Comment> commentList = commentRepository.findAllByPostIdOrderByModifiedAtDesc(postId);
        List<CommentResponseDto> commentResponseDtoList = commentList.stream().map(comment -> new CommentResponseDto(comment)).collect(Collectors.toList());
        return commentResponseDtoList;
    }

    // 댓글 수정
    public Comment updateComment(Long commentId, String comment) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isEmpty()) {
            throw new RuntimeException("값이 비어 있습니다.");
        }

        comment.setComment(comment);
        return commentRepository.save(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}

