package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.exceptions.CategoryAlreadyExistsException;
import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.repositories.CategoryRepository;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {

    private CategoryRepository categoryRepository;
    private CategoryServiceImpl categoryService;
    @BeforeEach
    void setUp(){
        categoryRepository = mock(CategoryRepository.class);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    void shouldCreateCategory() throws CategoryAlreadyExistsException {
        String name = "new category";
        Category expectedCategory = new Category(name);
        when(categoryRepository.existsByName(name)).thenReturn(false);
        when(categoryRepository.save(any())).thenReturn(expectedCategory);

        Category actualCategory = categoryService.create(name);
        assertEquals(expectedCategory, actualCategory);
    }

    @Test
    void shouldThrowExceptionWhenCreateCategoryWithAlreadyExists() throws CategoryAlreadyExistsException {
        String name = "new category";
        Category expectedCategory = new Category(name);
        when(categoryRepository.findByName(name)).thenReturn(expectedCategory);

        CategoryAlreadyExistsException exception = assertThrows(
                CategoryAlreadyExistsException.class,
                () -> categoryService.create(name)
        );
        assertThrows(CategoryAlreadyExistsException.class, ()->categoryService.create(name));
        assertEquals("The " + name + " category already exists !!", exception.getMessage());
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
        assertThrows(CategoryNotFoundByIdException.class, ()->categoryService.getById(id));
        assertEquals("Category with id " + id +" not found !!", exception.getMessage());
    }

    @Test
    void shouldReturnAllCategoriesWhenGetAll(){
        List<Category> expectedCategories = List.of(new Category("Category1"),
                new Category("Category2"),
                new Category("Category3"));
        when(categoryRepository.findAll()).thenReturn(expectedCategories);
        List<Category> actualCategories = categoryService.getAll();

        assertEquals(expectedCategories.size(), actualCategories.size());
        verify(categoryRepository).findAll();
    }

    @Test
    void shouldThrowExceptionWhenGetByInvalidId() {
        UUID id = UUID.randomUUID();
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        CategoryNotFoundByIdException thrownException = assertThrows(
                CategoryNotFoundByIdException.class,
                () -> categoryService.getById(id)
        );

        assertEquals("Category with id " + id +" not found !!", thrownException.getMessage());
    }

    @Test
    void shouldUpdateCategory() throws CategoryNotFoundByIdException, CategoryAlreadyExistsException {
        UUID id = UUID.randomUUID();
        String oldName = "Old";
        String expectedName = "Updated";
        Category oldCategory = new Category(oldName);
        Category expectedCategory = new Category(expectedName);
        when(categoryRepository.findByName(oldName)).thenReturn(oldCategory);
        when(categoryRepository.findById(id)).thenReturn(Optional.of(oldCategory));
        when(categoryRepository.save(any())).thenReturn(expectedCategory);

        Category actualCategory = categoryService.update(id,expectedName);
        assertEquals(expectedCategory.getName(),actualCategory.getName());
    }

    @Test
    void shouldThrowCategoryNotFoundExceptionWhenUpdate() throws CategoryNotFoundByIdException {
        UUID id = UUID.randomUUID();
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        CategoryNotFoundByIdException thrownException = assertThrows(
                CategoryNotFoundByIdException.class,
                () -> categoryService.update(id,"new name")
        );

        assertEquals("Category with id " + id +" not found !!", thrownException.getMessage());
    }

    @Test
    void shouldThrowCategoryNameAlreadyExistsExceptionWhenUpdate()  {
        UUID id = UUID.randomUUID();
        String name = "nameAlreadyExists";
        Category category = new Category("old name");
        Category category2 = new Category(name);
        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        when(categoryRepository.findByName(name)).thenReturn(category2);

        CategoryAlreadyExistsException thrownException = assertThrows(
                CategoryAlreadyExistsException.class,
                () -> categoryService.update(id,name)
        );

        assertEquals("The " + name + " category already exists !!", thrownException.getMessage());
    }

    @Test
    void shouldDeleteWhenDelete() throws CategoryNotFoundByIdException {
        UUID id = UUID.randomUUID();
        Category category = new Category("cat");

        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        doNothing().when(categoryRepository).deleteById(id);

        categoryService.deleteById(id);

        verify(categoryRepository).deleteById(id);
    }

    @Test
    void shouldThrowExceptionWhenDelete(){
        UUID id = UUID.randomUUID();

        when(categoryRepository.findById(id)).thenReturn(Optional.empty());
        CategoryNotFoundByIdException thrownException = assertThrows(
                CategoryNotFoundByIdException.class,
                () -> categoryService.deleteById(id)
        );

        assertEquals("Category with id " + id +" not found !!", thrownException.getMessage());
    }

}