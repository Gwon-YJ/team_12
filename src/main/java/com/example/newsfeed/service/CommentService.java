package com.example.newsfeed.service;

import com.example.newsfeed.dto.CommentResponseDto;
import com.example.newsfeed.entity.Comment;
import com.example.newsfeed.repository.CommentRepository;
import com.example.newsfeed.post.entity.Post;
import com.example.newsfeed.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    // 댓글 생성
    public CommentResponseDto saveComment(Long postId, String comment) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if(optionalPost.isEmpty()) {
            throw new RuntimeException("게시글이 없습니다.");
        }
        Comment commentEntity = new Comment(comment);
        commentEntity.setPost(post);
        CommentResponseDto commentResponseDto = new CommentResponseDto(commentEntity);

       return commentResponseDto;
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
        Comment updateComment = optionalComment.get();
        updateComment.setComment(comment);

        return commentRepository.save(updateComment);
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}

