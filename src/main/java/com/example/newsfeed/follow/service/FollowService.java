package com.example.newsfeed.follow.service;

import com.example.newsfeed.entity.User;
import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorType;
import com.example.newsfeed.follow.dto.FollowResponseDto;
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

        // 팔로우를 거는 사람이랑 팔로우 대상이랑 id 가 같을 때 예외
        if(followingId.equals(follower.getUserId())){
            throw new CustomException(ErrorType.INAPPROPRIATE_APPROACH);
        }

        // 팔로우를 이미 했으면 예외
        Optional<Follow> checkFollow = followRepository.findByFollowingIdAndFollowerId(followingId, follower.getUserId());
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
    public String unFollow(Long followId, User follower) {
        Follow follow = followRepository.findById(followId)
                .orElseThrow(() -> new CustomException(ErrorType.ENTITY_NOT_FOUND));

        // 보안 체크: 현재 로그인한 사용자가 이 팔로우의 주인인지 확인
        if (!follow.getFollower().getUserId().equals(follower.getUserId())) {
            throw new CustomException(ErrorType.ACCESS_DENIED); // 너가 정의한 에러로 바꿔도 좋아
        }

        followRepository.delete(follow);
        return follow.getFollowing().getUserName() + "님을 언팔로우 하였습니다.";
    }

}
