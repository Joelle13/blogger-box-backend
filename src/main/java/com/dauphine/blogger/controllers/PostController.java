package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationCategoryRequest;
import com.dauphine.blogger.dto.CreationPostRequest;
import com.dauphine.blogger.dto.UpdateCategoryRequest;
import com.dauphine.blogger.dto.UpdatePostRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/v1/posts")
public class PostController {
    @PostMapping("")
    @Operation(
            summary = "New post",
            description = "Create a new post"
    )
    public String createPost(
            @Parameter (description = "The new post")
            @RequestBody CreationPostRequest body){
        //TODO
        return "Create new post "+body;
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a  post",
            description = "Updating a post"
    )
    public String updatePost(
            @Parameter(description = "Id of the post to update")
            @PathVariable UUID id,
            @Parameter (description = "the post updated")
            @RequestBody UpdatePostRequest body){
        //TODO
        return "Updating a post";

    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete post",
            description = "Deleting a  post"
    )
    public String deletePost(
            @Parameter (description = "Id of the post to delete")
            @PathVariable UUID id){
        //TODO
        return "Deleting post " + id;
    }

    @GetMapping("")
    @Operation(
            summary = "Ordered posts",
            description = "All posts ordered by creation date"
    )
    public String postDate(){
        return "All posts";
    }
}
