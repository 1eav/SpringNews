package org.example.springexample.service;

import org.example.springexample.models.News;
import org.example.springexample.service.repository.NewsService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class InMemoryNewsService implements NewsService {
    private final ConcurrentHashMap<Long, News> newsMap = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    @Override
    public News getById(Long id) {
        return newsMap.get(id);
    }

    @Override
    public List<News> getAll() {
        return newsMap.values().stream().toList();
    }

    @Override
    public News create(News news) {
        Long id = idGenerator.getAndIncrement();
        news.setId(id);
        news.setDate(Instant.now());
        newsMap.put(id, news);
        System.out.println("Create ID");
        return news;
    }

    @Override
    public News update(News news) {
        Long id = news.getId();
        if (newsMap.contains(id)) {
            news.setDate(Instant.now());
            newsMap.put(id, news);
            System.out.println("Update by ID:" + id);
            return news;
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("Delete by ID: " + id);
        newsMap.remove(id);
    }
}