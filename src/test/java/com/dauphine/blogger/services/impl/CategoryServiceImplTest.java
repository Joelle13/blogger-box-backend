package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.repositories.CategoryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    private CategoryRepository categoryRepository;
    private CategoryServiceImpl categoryService;
    @BeforeEach
    void setUp(){
        categoryRepository = mock(CategoryRepository.class);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    void shouldCreateCategory(){
        String name = "new category";
        when(categoryRepository.existsByName(name)).thenReturn(false);
    }

    @Test
    void shouldReturnCategoryWhenGetById() throws CategoryNotFoundByIdException {
        UUID id = UUID.randomUUID();
        Category expected =  new Category("Category");
        when(categoryRepository.findById(id)).thenReturn(Optional.of(expected));

        Category actual = categoryService.getById(id);

        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowExceptionWhenNotFoundById() {
        UUID id = UUID.randomUUID();
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        CategoryNotFoundByIdException exception = assertThrows(
                CategoryNotFoundByIdException.class,
                () -> categoryService.getById(id)
        );

        assertEquals("Category with id " + id + " not found", exception.getMessage());
    }

    @Test
    void shouldReturnCategoryWhenGetBdValidId() throws CategoryNotFoundByIdException {
        //TODO
    }

    @Test
    void shouldThrowExceptionWhenGetByInvalidId() {
        UUID id = UUID.randomUUID();
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        CategoryNotFoundByIdException thrownException = assertThrows(
                CategoryNotFoundByIdException.class,
                () -> categoryService.getById(id)
        );

        assertEquals("Category with id " + id + " not found", thrownException.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCreatingCategoryWithExistingName() {}
}