package com.dauphine.blogger.services;

import com.dauphine.blogger.exceptions.CategoryAlreadyExistsException;
import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.models.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAll();
    Category getById(UUID id) throws CategoryNotFoundByIdException;
    Category create (String name) throws CategoryAlreadyExistsException;
    Category update(UUID id, String name) throws CategoryNotFoundByIdException;
    void deleteById(UUID id);

    List<Category> getAllByName(String name);
}
