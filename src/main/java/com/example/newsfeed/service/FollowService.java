package com.example.newsfeed.service;

import com.example.newsfeed.config.JwtUtil;
import com.example.newsfeed.entity.Comment;
import com.example.newsfeed.entity.Post;
import com.example.newsfeed.entity.User;
import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorType;
import com.example.newsfeed.dto.FollowResponseDto;
import com.example.newsfeed.entity.Follow;
import com.example.newsfeed.repository.FollowRepository;
import com.example.newsfeed.repository.PostRepository;
import com.example.newsfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;

    // 팔로우 생성
    public String follow(Long followingId, String token) {


        String customId = jwtUtil.extractCustomId(token);

        User user = userRepository.findByCustomId(customId);

        //토큰 만료 확인
        if(!jwtUtil.validateToken(token))
            throw new CustomException(ErrorType.TOKEN_EXPIRED);


        User follower = userRepository.findByCustomId(customId);
        Long followerId = follower.getUserId();

        Follow followEntity = new Follow();

//        followEntity.setUser(user);

        // 팔로우를 거는 사람이랑 팔로우 대상이랑 id 가 같을 때 예외
        if(followingId.equals(followerId)){
            throw new CustomException(ErrorType.INAPPROPRIATE_APPROACH); // 아빠!! 우리가 해냈어!!
        }

        // 팔로우를 이미 했으면 예외
        Optional<Follow> checkFollow = followRepository.findByFollowingUserIdAndFollowerUserId(followingId, followerId);
        if(checkFollow.isPresent()){
            throw new CustomException(ErrorType.DUPLICATE_TASK);
        }

        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new CustomException(ErrorType.ENTITY_NOT_FOUND));

        Follow follow = new Follow(follower, following);
        followRepository.save(follow);

        return following.getUserName() + "님을 팔로우하였습니다.";
    }

    // 유저 팔로우 조회(유저가 팔로우한)
    public FollowResponseDto followingList(Long userId) {

        // 유저가 존재하는지 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorType.ENTITY_NOT_FOUND));

        List<String> followingUserList = new ArrayList<>();
        for(Follow follow : user.getFollowingList()){
            followingUserList.add(follow.getFollowing().getUserName());
        }

        return new FollowResponseDto(followingUserList);
    }

    // 유저 팔로워 조회(유저를 팔로우한)
    public FollowResponseDto followerList(Long userId) {


        // 유저가 존재하는지 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorType.ENTITY_NOT_FOUND));

        List<String> followerUserList = new ArrayList<>();
        for(Follow follow : user.getFollowerList()){
            followerUserList.add(follow.getFollower().getUserName());
        }

        return new FollowResponseDto(followerUserList);
    }

    // 팔로우 취소
    public String unFollow(Long followId, String token) {

        Optional<Follow> optionalFollow = followRepository.findById(followId);

        if(optionalFollow.isEmpty()){
            throw new CustomException(ErrorType.ENTITY_NOT_FOUND);
        }

        Follow follow = optionalFollow.get();

        String customId = jwtUtil.extractCustomId(token);

        if(!follow.getFollower().getCustomId().equals(customId)){
            throw new CustomException(ErrorType.ACCESS_DENIED);
        } //  안 되면 버려 안 해! 저 근데 내일 못 자여 하하 살려줘여

        followRepository.delete(follow);
        return follow.getFollowing().getUserName() + "님을 언팔로워 했습니다.";
    }
}
