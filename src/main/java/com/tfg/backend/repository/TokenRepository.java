package com.tfg.backend.repository;

import com.tfg.backend.entity.DeviceToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository
        extends JpaRepository<DeviceToken, Long> {

    Optional<DeviceToken> findByToken(String token);

}