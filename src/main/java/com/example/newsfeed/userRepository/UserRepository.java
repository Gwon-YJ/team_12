package com.example.newsfeed.userRepository;

import com.example.newsfeed.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByUserNameOrderByModifiedAtDesc(String userName);

    Optional<User> findByEmail(String email);

}
