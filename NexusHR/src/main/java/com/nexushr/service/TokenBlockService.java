package com.nexushr.service;

public interface TokenBlockService {

    void blockToken(String token);

    boolean isBlocked(String token);

}