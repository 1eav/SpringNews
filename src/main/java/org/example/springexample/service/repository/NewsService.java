package org.example.springexample.service.repository;

import org.example.springexample.models.News;

import java.util.List;

public interface NewsService {
    News getById(Long id);
    List<News> getAll();
    News create(News news);
    News update(News news);
    void deleteById(Long id);
}