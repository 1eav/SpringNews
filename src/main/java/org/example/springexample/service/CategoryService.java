package org.example.springexample.service;

import org.example.springexample.models.Category;
import java.util.List;

public interface CategoryService {
    Category getById(Long id);
    List<Category> getAll();
    Category create(Category category);
    Category update(Category category);
    void deleteById(Long id);
}