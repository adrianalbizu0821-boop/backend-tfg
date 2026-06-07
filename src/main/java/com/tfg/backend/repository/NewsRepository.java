package com.tfg.backend.repository;

import com.tfg.backend.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository
        extends JpaRepository<News, Long> {
}