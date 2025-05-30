package com.example.newsfeed.service;

import com.example.newsfeed.dto.PostPageInfoResponseDto;
import com.example.newsfeed.dto.PostPageResponseDto;
import com.example.newsfeed.entity.Post;
import com.example.newsfeed.dto.PostResponseDto;
import com.example.newsfeed.entity.User;
import com.example.newsfeed.repository.PostRepository;
import com.example.newsfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 게시글 생성
    @Transactional
    public PostResponseDto createPost(Long userId, String title, String content) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다."));

        Post post = new Post(title, content, user);

        Post createPost = postRepository.save(post);

        return new PostResponseDto(
                createPost.getPostId(),
                createPost.getUser().getUserName(),
                createPost.getTitle(),
                createPost.getContent(),
                createPost.getLikesCount(),
                createPost.getCommentsCount(),
                createPost.getCreatedAt(),
                createPost.getModifiedAt());
        // <- 임시로 지정해놓은 리턴값, 위에 주석 해제 후 삭제
    }

    // 게시글 수정
    @Transactional
    public PostResponseDto updatePost(Long postId, Long userId, String title, String content) {

        // 입력받은 postId를 찾아서 post 변수에 저장
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));

        // 지금 게시글을 쓴 유저 id 와 입력받은 유저 id 가 다르면 예외처리
        if (!post.getUser().getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "작성자만 게시글을 수정할 수 있습니다.");
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

//        if(!userRepository.existsById(userId)){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 유저가 존재하지 않습니다.");
//        }

        return postRepository.findByPostId(userId)
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
    public void deletePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다."));

        if(!post.getUser().getUserId().equals(userId)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "작성자만 게시글을 삭제할 수 있습니다.");
        }

        postRepository.delete(post);
    }
}
