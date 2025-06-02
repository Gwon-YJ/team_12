package com.example.newsfeed;

import com.example.newsfeed.entity.User;
import com.example.newsfeed.enums.UserRoleEnum;
import com.example.newsfeed.service.UserService;
import com.example.newsfeed.utils.PasswordEncoder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

 //   @PostConstruct 테스트 용이라 주석처리 해둠
    @Transactional
    public void init() {
        List<User> productList =
                List.of(new User("권용준", passwordEncoder.encode("1234"), "yoyo99828@naver.com", UserRoleEnum.USER),
                        new User("박소희", passwordEncoder.encode("1234"), "user2@sparta.com", UserRoleEnum.USER),
                        new User("이형준", passwordEncoder.encode("1234"), "user3@sparta.com", UserRoleEnum.USER),
                        new User("임서연", passwordEncoder.encode("1234"), "user4@sparta.com", UserRoleEnum.USER),
                        new User("강산", passwordEncoder.encode("1234"), "user5@sparta.com", UserRoleEnum.USER));
        for (User product : productList) {
            userService.save(product);
        }
    }
}
