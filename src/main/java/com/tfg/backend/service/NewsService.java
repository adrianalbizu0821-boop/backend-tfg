package com.tfg.backend.service;

import com.tfg.backend.dto.CreateNewsRequest;
import com.tfg.backend.entity.News;
import com.tfg.backend.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;
    private final FcmService fcmService;

    public NewsService(
            NewsRepository newsRepository,
            FcmService fcmService) {

        this.newsRepository = newsRepository;
        this.fcmService = fcmService;
    }

    public News createNews(CreateNewsRequest request) {

        News news = new News();

        news.setTitle(request.getTitle());
        news.setSummary(request.getSummary());
        news.setContent(request.getContent());
        news.setImageUrl(request.getImageUrl());
        news.setPublishedAt(LocalDateTime.now());

        News savedNews = newsRepository.save(news);

        fcmService.sendToAll(
                savedNews.getTitle(),
                savedNews.getSummary(),
                savedNews.getId().toString()
        );

        return savedNews;
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public News getNewsById(Long id) {

        return newsRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Noticia no encontrada"));
    }

    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }

    public News updateNews(
            Long id,
            CreateNewsRequest request) {

        News news = newsRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Noticia no encontrada"));

        news.setTitle(request.getTitle());
        news.setSummary(request.getSummary());
        news.setContent(request.getContent());
        news.setImageUrl(request.getImageUrl());

        return newsRepository.save(news);
    }
}