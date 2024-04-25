package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final List<Category> temporaryCategories;

    public CategoryServiceImpl() {
        this.temporaryCategories = new ArrayList<>();
        temporaryCategories.add(new Category(UUID.randomUUID(), "my first category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my second category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my third category"));
    }


    @Override
    public List<Category> getAll() {
        return temporaryCategories;
    }

    @Override
    public Category getById(UUID id) {
        return temporaryCategories.stream()
                .filter(category -> id.equals(category.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Category create(String name) {
        Category c = new Category(UUID.randomUUID(),name);
        temporaryCategories.add(c);
        return c;
    }

    @Override
    public Category update(UUID id, String name) {
        Category category = temporaryCategories.stream()
                .filter(c -> id.equals(c.getId()))
                .findFirst()
                .orElse(null);
        if (category != null){
            category.setName(name);
        }
        return category;
    }

    @Override
    public void deleteById(UUID id) {
        temporaryCategories.removeIf(category -> id.equals(category.getId()));
    }
}