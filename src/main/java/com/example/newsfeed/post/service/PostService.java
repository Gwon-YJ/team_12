package com.example.newsfeed.post.service;

import com.example.newsfeed.entity.User;
import com.example.newsfeed.post.entity.Post;
import com.example.newsfeed.post.dto.PostResponseDto;
import com.example.newsfeed.post.repository.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
//    private final UserRepository userRepository;

    // 게시글 생성
    public PostResponseDto createPost(HttpServletRequest servletRequest, String title, String content) {

        // 기존 세션이 있으면 그 세션을 반환하고 없으면 null 을 반환
        HttpSession session = servletRequest.getSession(false);

//        User loginUser = (User) session.getAttribute(Const.LOGIN_USER);

//        if(loginUser == null){
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인한 사용자만 이용하실 수 있습니다.");
//        }

        Post post = new Post(title, content);
//        post.setUser(loginUser);

        Post createPost = postRepository.save(post);

        return new PostResponseDto(
                createPost.getPostId(),
                createPost.getUser().getUsername(),
                createPost.getTitle(),
                createPost.getContent(),
                createPost.getLikesCount(),
                createPost.getCommentsCount(),
                createPost.getCreatedAt(),
                createPost.getModifiedAt());
    }

    // 게시글 수정
    public PostResponseDto updatePost(Long postId, HttpServletRequest servletRequest, String title, String content) {

        // 입력받은 postId를 찾아서 post 변수에 저장
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."));

        // 세션에서 이메일을 불러오고 업데이트할 스케줄을 쓴 유저와 이메일이 다르면 예외 발생
        HttpSession session = servletRequest.getSession(false);
//        User loginUser = (User) session.getAttribute(Const.LOGIN_USER);

//        if(loginUser.getEmail().equals(post.getUser().getEmail())){
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인한 유저가 작성한 글이 없습니다.");
//        }

        post.setTitle(title);
        post.setContent(content);

        // 변경된 post 를 DB에 저장하고 저장된 결과를 updatedPost 에 저장
        Post updatedPost = postRepository.save(post);

        return new PostResponseDto(
                updatedPost.getPostId(),
                updatedPost.getUser().getUsername(),
                updatedPost.getTitle(),
                updatedPost.getContent(),
                updatedPost.getLikesCount(),
                updatedPost.getCommentsCount(),
                updatedPost.getCreatedAt(),
                updatedPost.getModifiedAt());

    }

    // 특정 유저 게시글 조회
    public List<PostResponseDto> userPosts(Long userId) {

//       userRepository.findById(userId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 유저가 존재하지 않습니다."));

        return postRepository.findByUserId(userId)
                .stream()
                .map(PostResponseDto::toDto)
                .toList();
    }

    // 게시글 페이징 조회(팔로우한 사람의 게시물을 최신순으로)

    // 게시글 날짜 범위로 검색

    // 게시글 삭제
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다."));

        postRepository.delete(post);
    }
}
