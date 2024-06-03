package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.exceptions.CategoryAlreadyExistsException;
import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.categoryRepository = repository;
    }


    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(UUID id) throws CategoryNotFoundByIdException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundByIdException());
    }

    @Override
    public Category create(String name) throws CategoryAlreadyExistsException {
        if(categoryRepository.findByName(name) != null){
            throw new CategoryAlreadyExistsException();
        }
        Category c = new Category(name);
        return categoryRepository.save(c);
    }

    @Override
    public Category update(UUID id, String name) throws CategoryNotFoundByIdException {
        Category category = getById(id);
        if(category == null){
            throw new CategoryNotFoundByIdException();
        }
        category.setName(name);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(UUID id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> getAllByName(String name) {
        return categoryRepository.findAllByName(name);
    }
}
