package com.nexushr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexushr.entity.BlockedToken;

public interface BlockedTokenRepository extends JpaRepository<BlockedToken, Long> {

    boolean existsByToken(String token);

}