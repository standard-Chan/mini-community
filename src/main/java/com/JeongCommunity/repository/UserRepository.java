package com.JeongCommunity.repository;

import com.JeongCommunity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 자동으로 쿼리 생성 : FROM users WHERE email = ${email}
    Optional<User> findByEmail(String email);
}
