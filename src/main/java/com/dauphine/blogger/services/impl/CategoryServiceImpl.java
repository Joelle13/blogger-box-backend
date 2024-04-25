package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final List<Category> temporaryCategories;
    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
        this.temporaryCategories = new ArrayList<>();
        temporaryCategories.add(new Category(UUID.randomUUID(), "my first category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my second category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my third category"));
    }


    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public Category getById(UUID id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public Category create(String name) {
        Category c = new Category(UUID.randomUUID(),name);
        return repository.save(c);
    }

    @Override
    public Category update(UUID id, String name) {
        Category category = getById(id);
        if(category == null){
            return null;
        }
        category.setName(name);
        return repository.save(category);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
