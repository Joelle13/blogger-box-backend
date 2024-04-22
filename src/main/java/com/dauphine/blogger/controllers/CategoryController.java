package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationCategoryRequest;
import com.dauphine.blogger.dto.UpdateCategoryRequest;
import com.dauphine.blogger.dto.UpdatePostRequest;
import com.dauphine.blogger.models.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {
    private final List<Category> temporaryCategories;

    public CategoryController(){
        temporaryCategories = new ArrayList<>();
        temporaryCategories.add(new Category(UUID.randomUUID(),"my first category"));
        temporaryCategories.add(new Category(UUID.randomUUID(),"my second category"));
        temporaryCategories.add(new Category(UUID.randomUUID(),"my third category"));
    }

    @GetMapping("")
    @Operation(
            summary = "Retrieve all categories"
    )
    public String categories(){
        //TODO
        return "All categories";
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Category by id endpoint",
            description = "Returns the category with the corresponding id by path variable"
    )
    public String categoryById(
            @Parameter(description = "Id of the category")
            @PathVariable UUID id){
        //TODO
        return "Category " + id;
    }

    @PostMapping("")
    @Operation(
            summary = "New category",
            description = "Create a new category"
    )
    public String createCategory(
            @Parameter (description = "The new category")
            @RequestBody CreationCategoryRequest body){
        //TODO
        return "Create new category "+body;
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update category",
            description = "Update a category"
    )
    public String updateCategory(
            @Parameter (description = "Id of the category to update")
            @PathVariable UUID id,
            @Parameter (description = "Category updated")
            @RequestBody UpdateCategoryRequest body){
        //TODO
        return "Updating a category";

    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete category",
            description = "Delete a category by id"
    )
    public String deleteCategory(
            @Parameter (description = "Id of the category to delete")
            @PathVariable UUID id){
        //TODO
        return "Deleting category " + id;
    }

    @GetMapping("/{id}/posts")
    @Operation(
            summary = "Posts by category",
            description = "Get all post of a certain categories"
    )
    public String postCategories(
            @Parameter (description = "Id of the category")
            @PathVariable UUID id){
        //TODO
        return "posts from the category " + id;
    }
}
