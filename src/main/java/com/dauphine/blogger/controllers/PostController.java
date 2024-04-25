package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationPostRequest;
import com.dauphine.blogger.dto.UpdatePostRequest;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("")
    @Operation(
            summary = "New post",
            description = "Create a new post"
    )
    public Post createPost(
            @Parameter (description = "Post's title")
            @RequestBody String title,
            @Parameter (description = "Post's content")
            @RequestBody String content,
            @Parameter (description = "Post's category")
            @PathVariable Category category){
        return postService.create(title, content, category);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a  post",
            description = "Updating a post"
    )
    public Post updatePost(
            @Parameter(description = "Id of the post to update")
            @PathVariable UUID id,
            @Parameter (description = "title")
            @RequestBody String title,
            @Parameter (description = "content")
            @RequestBody String content){
        return postService.update(id, title, content);

    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete post",
            description = "Deleting a  post"
    )
    public boolean deletePost(
            @Parameter (description = "Id of the post to delete")
            @PathVariable UUID id){
        return postService.deleteById(id);
    }

    @GetMapping("categories/{id}/posts")
    @Operation(
            summary = "Posts by category",
            description = "Get all post of a certain categories"
    )
    public List<Post> postCategories(
            @Parameter (description = "Id of the category")
            @PathVariable UUID id){
        return postService.getAllByCategoryId(id);
    }

    @GetMapping("")
    @Operation(
            summary = "Ordered posts",
            description = "All posts ordered by creation date"
    )
    public List<Post> postDate(){
        return postService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Post by id",
            description = "Post by ID given"
    )
    public Post getPost(
            @Parameter (description = "Id of the post")
            @PathVariable UUID id){
        return postService.getById(id);
    }

}
