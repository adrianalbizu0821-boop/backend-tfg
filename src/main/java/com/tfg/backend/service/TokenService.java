package com.tfg.backend.service;

import com.tfg.backend.dto.RegisterTokenRequest;
import com.tfg.backend.entity.DeviceToken;
import com.tfg.backend.repository.TokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public DeviceToken saveToken(RegisterTokenRequest request) {

        return tokenRepository.findByToken(request.getToken())
                .orElseGet(() -> {

                    DeviceToken token = new DeviceToken();

                    token.setUserId(request.getUserId());
                    token.setToken(request.getToken());
                    token.setPlatform(request.getPlatform());
                    token.setCreatedAt(LocalDateTime.now());

                    return tokenRepository.save(token);
                });
    }
    public List<DeviceToken> getAllTokens() {
        return tokenRepository.findAll();
    }
    @Transactional
    public void deleteToken(String token) {
        tokenRepository.deleteByToken(token);
    }
}