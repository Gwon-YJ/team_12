package com.example.newsfeed.service;

import com.example.newsfeed.dto.LikesResponseDto;
import com.example.newsfeed.entity.Likes;
import com.example.newsfeed.entity.Post;
import com.example.newsfeed.entity.User;
import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorType;
import com.example.newsfeed.repository.LikesRepository;
import com.example.newsfeed.repository.PostRepository;
import com.example.newsfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LikesService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikesRepository likesRepository;

    @Transactional//더티 체킹활용
    public LikesResponseDto signup(Long likesId, String username, Long likesCount) {
        Post post = postRepository.findById(likesId).orElseThrow(()->new CustomException(ErrorType.ENTITY_NOT_FOUND));
        // PostException.PostNotFoundException::new == () -> new PostException.PostNotFoundException
        //에러 타입 선언(() -> new CustomException(ErrorType.ENTITY_NOT_FOUND));//post 있으면 아이디에 해당하는 포스트를 객체 담는다고 / 예외처리를 던져라

        User foundUser = userRepository.findByuserId(likesId);
        log.info("성공");

        Likes likes = new Likes(foundUser, likesCount);
        Likes createLikes = likesRepository.save(likes);
        post.setLikesCount(createLikes.getLikeCount());
        //위 기능 중 하나라도 에러나면 수정 사항반영 X > 로백(기존 상태 유지)

        if (!post.getUser().getUsername().equals(username)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "작성자만 좋아요를 생성할 수 있습니다.");
        }

        return new LikesResponseDto(post.getPostId(), post.getUser().getUsername(), post.getTitle(),
                post.getContent(), post.getLikesCount(), post.getCommentsCount(), post.getCreatedAt(), post.getModifiedAt());

    }

    public void deleteLikes(Long Likesid, String username) {
        Optional<Post> postId = postRepository.findById(Likesid);
        Likes likesbyId = likesRepository.findByLikseId(Likesid);

        Post post = postId.get();
        log.info("성공");

        if (!post.getUser().getUsername().equals(username)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "작성자만 게시글을 삭제할 수 있습니다.");
        }

        likesRepository.delete(likesbyId);
    }


}