package com.sparta.week05miniprojectbackend.repository;

import com.sparta.week05miniprojectbackend.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByAccountUserId(String userId);
}
