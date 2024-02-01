package org.example.springexample.controller;

import org.example.springexample.models.Category;
import org.example.springexample.models.News;
import org.example.springexample.service.CategoryService;
import org.example.springexample.service.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsService newsService;
    private final CategoryService categoryService;

    public NewsController(NewsService newsService, CategoryService categoryService) {
        this.newsService = newsService;
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> getById(@PathVariable Long id) {
        News newsServiceById = newsService.getById(id);
        return (newsServiceById != null) ? ResponseEntity.ok(newsServiceById) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<News> getAll() {
        return newsService.getAll();
    }

    @PostMapping()
    public ResponseEntity<News> create(@RequestBody News news) {
        Category existingCategory = categoryService.getById(news.getCategory().getId());
        if (existingCategory == null) {
            return ResponseEntity.notFound().build();
        }

        news.setCategory(existingCategory);
        News createdNews = newsService.create(news);
        return new ResponseEntity<>(createdNews, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<News> update(@RequestBody News news) {
        News updateNews = newsService.update(news);
        return (updateNews != null) ? ResponseEntity.ok(updateNews) : ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        newsService.deleteById(id);
        return ResponseEntity.notFound().build();
    }
}