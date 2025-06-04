package com.example.newsfeed.service;

import com.example.newsfeed.config.JwtUtil;
import com.example.newsfeed.dto.CommentResponseDto;
import com.example.newsfeed.entity.Comment;
import com.example.newsfeed.entity.User;
import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorType;
import com.example.newsfeed.repository.CommentRepository;
import com.example.newsfeed.entity.Post;
import com.example.newsfeed.repository.PostRepository;
import com.example.newsfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // 댓글 생성
    public CommentResponseDto saveComment(Long postId,String token,String comment) {
        Optional<Post> optionalPost = postRepository.findById(postId);

        String customId = jwtUtil.extractCustomId(token);

        User user = userRepository.findByCustomId(customId);

        if(optionalPost.isEmpty()) {
            throw new RuntimeException("게시글이 없습니다.");
        }

        long createComments = 0L;
        createComments = createComments + 1;


        Post post = optionalPost.get();
        Comment commentEntity = new Comment(comment,createComments);
        commentEntity.setPost(post);
        commentEntity.setUser(user);
        Comment createComment = commentRepository.save(commentEntity); //사실 인스타갬성으로 좋아요 개수랑 댓글 개수만 게시글이랑 표시하려고했죵
        post.setCommentsCount(createComment.getCommentsCount());

        return new CommentResponseDto(post.getPostId(),post.getUser().getUserName(),
                post.getTitle(),post.getContent(),comment,post.getLikesCount(),post.getCommentsCount(),
                post.getCreatedAt(),post.getModifiedAt());
    }


    // 댓글 삭제
    public void deleteComment (Long postId,Long commentId,String token){
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Optional<Post> optionaPost = postRepository.findById(postId);

        String customId = jwtUtil.extractCustomId(token);

        //토큰 만료 여부 검증
        if(!jwtUtil.validateToken(token))
            throw new CustomException(ErrorType.TOKEN_EXPIRED);

        //게시글 존재 여부 검증
        if(optionaPost.isEmpty()){
            throw new CustomException(ErrorType.ENTITY_NOT_FOUND);
        }

        Post post = optionaPost.get();

        //댓글 존재 여부 검증
        if (optionalComment.isEmpty()) {
            throw new CustomException(ErrorType.ENTITY_NOT_FOUND);
        }

        //권한 검증
//        if(!post.getUser().getUserId().equals(customId)){
//            throw new CustomException(ErrorType.ACCESS_DENIED);
//        }

        Comment deleteComment = optionalComment.get();

        commentRepository.delete(deleteComment);
    }

}
