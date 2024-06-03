package com.dauphine.blogger.controllers;

import com.dauphine.blogger.exceptions.CategoryAlreadyExistsException;
import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Category by id endpoint",
            description = "Returns the category with the corresponding id by path variable"
    )
    public ResponseEntity<Category> categoryById (
            @Parameter(description = "Id of the category")
            @PathVariable UUID id) throws CategoryNotFoundByIdException {
        Category category = categoryService.getById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping("")
    @Operation(
            summary = "New category",
            description = "Create a new category"
    )
    public ResponseEntity<Category> createCategory(
            @Parameter (description = "The new category")
            @RequestBody String name) throws CategoryAlreadyExistsException {
        Category category = categoryService.create(name);
        return ResponseEntity
                .created(URI.create("v1/categories/"+category.getId()))
                .body(category);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update category",
            description = "Update a category"
    )
    public ResponseEntity<Category> updateCategory(
            @Parameter (description = "Id of the category to update")
            @PathVariable UUID id,
            @Parameter (description = "Name of the category")
            @RequestBody String name) throws CategoryNotFoundByIdException, CategoryAlreadyExistsException {
        Category category = categoryService.update(id,name);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete category",
            description = "Delete a category by id"
    )
    public ResponseEntity<?> deleteCategory(
            @Parameter (description = "Id of the category to delete")
            @PathVariable UUID id) throws CategoryNotFoundByIdException {
        categoryService.getById(id);
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        List<Category> categories =categoryService.getAll();
        return ResponseEntity.ok(categories);
    }
}
