package org.example.springexample.service;

import org.example.springexample.models.News;
import org.example.springexample.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public News getById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }

    @Override
    public List<News> getAll() {
        return newsRepository.findAll();
    }

    @Override
    public News create(News news) {
        return newsRepository.save(news);
    }

    @Override
    public News update(News news) {
        if (newsRepository.existsById(news.getId())) {
            return newsRepository.save(news);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }
}