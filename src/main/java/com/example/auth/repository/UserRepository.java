package com.example.auth.repository;


import com.example.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> { //user 정보
    Optional<User> findByUid(String email);
}