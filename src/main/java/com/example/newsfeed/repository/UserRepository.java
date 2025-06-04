package com.example.newsfeed.repository;


import com.example.newsfeed.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    //boolean existsByUsername(String username);

    List<User> findAllByUserNameOrderByModifiedAtDesc(String userName);

    User findByuserId (Long Id);

    User findByCustomId(String customId);
}
