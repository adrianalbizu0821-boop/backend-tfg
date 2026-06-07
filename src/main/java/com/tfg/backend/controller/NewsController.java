package com.tfg.backend.controller;

import com.tfg.backend.dto.CreateNewsRequest;
import com.tfg.backend.entity.News;
import com.tfg.backend.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@CrossOrigin("*")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping
    public News createNews(
            @RequestBody CreateNewsRequest request) {

        return newsService.createNews(request);
    }

    @GetMapping
    public List<News> getAllNews() {

        return newsService.getAllNews();
    }

    @GetMapping("/{id}")
    public News getNewsById(
            @PathVariable Long id) {

        return newsService.getNewsById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteNews(
            @PathVariable Long id) {

        newsService.deleteNews(id);
    }

    @PutMapping("/{id}")
    public News updateNews(
            @PathVariable Long id,
            @RequestBody CreateNewsRequest request) {

        return newsService.updateNews(id, request);
    }
}