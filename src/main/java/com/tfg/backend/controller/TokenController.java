package com.tfg.backend.controller;

import com.tfg.backend.dto.RegisterTokenRequest;
import com.tfg.backend.entity.DeviceToken;
import com.tfg.backend.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tokens")
@CrossOrigin("*")
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<DeviceToken> saveToken(
            @RequestBody RegisterTokenRequest request) {

        return ResponseEntity.ok(
                tokenService.saveToken(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<DeviceToken>> getAllTokens() {

        return ResponseEntity.ok(
                tokenService.getAllTokens()
        );
    }

    @DeleteMapping("/{token}")
    public ResponseEntity<Void> deleteToken(
            @PathVariable String token) {

        tokenService.deleteToken(token);

        return ResponseEntity.noContent().build();
    }
}