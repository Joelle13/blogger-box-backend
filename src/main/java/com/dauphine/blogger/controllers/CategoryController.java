package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationCategoryRequest;
import com.dauphine.blogger.dto.UpdateCategoryRequest;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("")
    @Operation(
            summary = "Retrieve all categories"
    )
    public List<Category> categories(){
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Category by id endpoint",
            description = "Returns the category with the corresponding id by path variable"
    )
    public Category categoryById(
            @Parameter(description = "Id of the category")
            @PathVariable UUID id){
        return categoryService.getById(id);
    }

    @PostMapping("")
    @Operation(
            summary = "New category",
            description = "Create a new category"
    )
    public Category createCategory(
            @Parameter (description = "The new category")
            @RequestBody String name){
        return categoryService.create(name);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update category",
            description = "Update a category"
    )
    public Category updateCategory(
            @Parameter (description = "Id of the category to update")
            @PathVariable UUID id,
            @Parameter (description = "Name of the category")
            @RequestBody String name){
        return categoryService.update(id,name);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete category",
            description = "Delete a category by id"
    )
    public void deleteCategory(
            @Parameter (description = "Id of the category to delete")
            @PathVariable UUID id){
         categoryService.deleteById(id);
    }

    /*@GetMapping("/{id}/posts")
    @Operation(
            summary = "Posts by category",
            description = "Get all post of a certain categories"
    )
    public String postCategories(
            @Parameter (description = "Id of the category")
            @PathVariable UUID id){
        return "posts from the category " + id;
    }*/
}
