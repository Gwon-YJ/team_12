package com.example.newsfeed.follow.service;

import com.example.newsfeed.entity.User;
import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorType;
import com.example.newsfeed.follow.dto.FollowResponseDto;
import com.example.newsfeed.follow.dto.FollowUserDto;
import com.example.newsfeed.follow.entity.Follow;
import com.example.newsfeed.follow.repository.FollowRepository;
import com.example.newsfeed.userRepository.UserRepository;
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

    // 팔로우 생성
    public String follow(Long followingId, User follower) {
        if(followingId.equals(follower.getUserId())){
            throw new CustomException(ErrorType.INAPPROPRIATE_APPROACH);
        }

        Optional<Follow> checkFollow = followRepository.findByFollowing_UserIdAndFollower_UserId(followingId, follower.getUserId());
        if(checkFollow.isPresent()){
            throw new CustomException(ErrorType.DUPLICATE_TASK);
        }

        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new CustomException(ErrorType.ENTITY_NOT_FOUND));

        Follow follow = new Follow(follower, following);
        followRepository.save(follow);

        return following.getUserName() + "님을 팔로우하였습니다.";
    }

    // 유저가 팔로우한 리스트 조회 (변경됨)
    public FollowResponseDto followingList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorType.ENTITY_NOT_FOUND));

        List<FollowUserDto> followingUserList = new ArrayList<>();
        for(Follow follow : user.getFollowingList()) {
            User following = follow.getFollowing();
            followingUserList.add(new FollowUserDto(following.getUserId(), following.getUserName()));
        }

        return new FollowResponseDto(followingUserList);
    }

    // 유저를 팔로우한 리스트 조회 (변경됨)
    public FollowResponseDto followerList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorType.ENTITY_NOT_FOUND));

        List<FollowUserDto> followerUserList = new ArrayList<>();
        for(Follow follow : user.getFollowerList()) {
            User follower = follow.getFollower();
            followerUserList.add(new FollowUserDto(follower.getUserId(), follower.getUserName()));
        }

        return new FollowResponseDto(followerUserList);
    }

    // 팔로우 취소
    public String unFollow(Long followId, User follower) {
        Follow follow = followRepository.findById(followId)
                .orElseThrow(() -> new CustomException(ErrorType.ENTITY_NOT_FOUND));

        if (!follow.getFollower().getUserId().equals(follower.getUserId())) {
            throw new CustomException(ErrorType.ACCESS_DENIED);
        }

        followRepository.delete(follow);
        return follow.getFollowing().getUserName() + "님을 어느팔로워 했습니다.";
    }
}
