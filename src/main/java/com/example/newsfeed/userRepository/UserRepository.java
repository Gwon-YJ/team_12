package com.example.newsfeed.userRepository;

import com.example.newsfeed.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByUserNameOrderByModifiedAtDesc(String userName);
}
