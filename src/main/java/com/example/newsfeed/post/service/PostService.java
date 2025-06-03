package com.example.newsfeed.post.service;

import com.example.newsfeed.entity.User;
import com.example.newsfeed.post.JwtUtil;
import com.example.newsfeed.post.dto.PostPageInfoResponseDto;
import com.example.newsfeed.post.dto.PostPageResponseDto;
import com.example.newsfeed.post.dto.PostResponseDto;
import com.example.newsfeed.post.entity.Post;
import com.example.newsfeed.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final JwtUtil jwtUtil;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 게시글 생성
    @Transactional
    public PostResponseDto createPost(String customId, String title, String content) {
        // DB에서 사용자 꼭 조회해야 함
        User user = (User) userRepository.findBycustomId(customId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post(title, content, user);
        Post savedPost = postRepository.save(post);

        return PostResponseDto.toDto(savedPost);
    }

    // 게시글 수정
    @Transactional
    public PostResponseDto updatePost(Long postId, String customId, String title, String content) {

        // 입력받은 postId를 찾아서 post 변수에 저장
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorType.ENTITY_NOT_FOUND));

//         지금 게시글을 쓴 유저 id 와 입력받은 유저 id 가 다르면 예외처리
        if (!post.getUser().getCustomId().equals(customId)) {
            throw new CustomException(ErrorType.ACCESS_DENIED);
        }

        post.setTitle(title);
        post.setContent(content);

        // 변경된 post 를 DB에 저장하고 저장된 결과를 updatedPost 에 저장
        Post updatedPost = postRepository.save(post);

        return new PostResponseDto(
                updatedPost.getPostId(),
                updatedPost.getUser().getUserName(),
                updatedPost.getTitle(),
                updatedPost.getContent(),
                updatedPost.getLikesCount(),
                updatedPost.getCommentsCount(),
                updatedPost.getCreatedAt(),
                updatedPost.getModifiedAt());

    }

    // 특정 유저 게시글 조회
    public List<PostResponseDto> userPosts(Long userId) {

        if(!userRepository.existsById(userId)){
            throw new CustomException(ErrorType.ENTITY_NOT_FOUND);
        }

        return postRepository.findByUserUserId(userId)
                .stream()
                .map(PostResponseDto::toDto)
                .toList();
    }

    // 게시글 페이징 조회(게시물을 최신순으로) 추후에 팔로우한 사람들만 보이게 수정 예정
    public PostPageInfoResponseDto pagingPosts(int page, int size) {

        Page<Post> postPage = postRepository.findAll(PageRequest.of(page - 1, size, Sort.by("createdAt").descending()));

        List<PostPageResponseDto> list = postPage
                .stream()
                .map(PostPageResponseDto::toDto)
                .toList();

        return PostPageInfoResponseDto.toDto(
                list,
                page,
                size,
                postPage.getTotalPages(),
                postPage.getTotalElements()
        );
    }

    // 게시글 날짜 범위로 검색
    public List<PostResponseDto> searchPostsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {

        Sort sort = Sort.by(Sort.Direction.DESC, "modifiedAt");

        return postRepository.findAllByCreatedAtBetween(startDate, endDate, sort)
                .stream()
                .map(PostResponseDto::toDto)
                .toList();
    }

    // 게시글 삭제
    @Transactional
    public void deletePost(Long postId, String customId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorType.ENTITY_NOT_FOUND));

        if (!post.getUser().getCustomId().equals(customId)) {
            throw new CustomException(ErrorType.ACCESS_DENIED);
        }

        postRepository.delete(post);
    }
}
