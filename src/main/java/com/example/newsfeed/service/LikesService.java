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
import org.hibernate.sql.results.jdbc.internal.JdbcValuesResultSetImpl;
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
    public LikesResponseDto signup(Long likesId, String customId, Long likesCount) {
        Optional<Post> optionalPost= postRepository.findById(likesId);
        Post post = optionalPost.get();
        // PostException.PostNotFoundException::new == () -> new PostException.PostNotFoundException
        //에러 타입 선언(() -> new CustomException(ErrorType.ENTITY_NOT_FOUND));//post 있으면 아이디에 해당하는 포스트를 객체 담는다고 / 예외처리를 던져라

        User foundUser = userRepository.findByuserId(likesId);

        Likes likes = new Likes(foundUser, likesCount);
        Likes createLikes = likesRepository.save(likes);
        post.setLikesCount(createLikes.getLikeCount());
        User user = userRepository.findByCustomId(customId);
        //위 기능 중 하나라도 에러나면 수정 사항반영 X > 로백(기존 상태 유지)

        if (!post.getUser().getCustomId().equals(customId)) {
            throw new CustomException(ErrorType.ACCESS_DENIED);
        }

        return new LikesResponseDto(post.getPostId(), post.getUser().getUserName(), post.getTitle(),
                post.getContent(), post.getLikesCount(), post.getCommentsCount(), post.getCreatedAt(), post.getModifiedAt());

    }

    public void deleteLikes(Long Likesid, String customId) {
        Optional<Post> postId = postRepository.findById(Likesid);
        Likes likesbyId = likesRepository.findByLikseId(Likesid);

        Post post = postId.get();

        if (!post.getUser().getCustomId().equals(customId)) {
            throw new CustomException(ErrorType.ACCESS_DENIED);
        }

        likesRepository.delete(likesbyId);
    }


}