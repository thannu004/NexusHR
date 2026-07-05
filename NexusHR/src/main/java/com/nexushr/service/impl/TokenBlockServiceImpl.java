package com.nexushr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexushr.entity.BlockedToken;
import com.nexushr.repository.BlockedTokenRepository;
import com.nexushr.service.TokenBlockService;

@Service
public class TokenBlockServiceImpl implements TokenBlockService {

    @Autowired
    private BlockedTokenRepository blockedTokenRepository;

    @Override
    public void blockToken(String token) {
        blockedTokenRepository.save(new BlockedToken(token));
    }

    @Override
    public boolean isBlocked(String token) {
        return blockedTokenRepository.existsByToken(token);
    }
}