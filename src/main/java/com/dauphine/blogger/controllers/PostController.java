package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationPostRequest;
import com.dauphine.blogger.dto.UpdatePostRequest;
import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.exceptions.PostNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<Post> createPost(
            @Parameter (description = "Post")
            @RequestBody CreationPostRequest post) throws CategoryNotFoundByIdException {
        Post p = postService.create(post);
        return ResponseEntity
                .created(URI.create("v1/posts/"+p.getId()))
                .body(p);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a  post",
            description = "Updating a post"
    )
    public ResponseEntity<Post> updatePost(@Parameter(description = "the post to update")
                                           UpdatePostRequest post) throws PostNotFoundByIdException {
        Post p =postService.update(post.getPostId(), post.getTitle(), post.getContent());
        return ResponseEntity.ok(p);

    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete post",
            description = "Deleting a  post"
    )
    public ResponseEntity<?> deletePost(
            @Parameter (description = "Id of the post to delete")
            @PathVariable UUID id) throws PostNotFoundByIdException {
        postService.getById(id);
        postService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("categories/{id}/posts")
    @Operation(
            summary = "Posts by category",
            description = "Get all post of a certain categories"
    )
    public ResponseEntity<List<Post>> postCategories(
            @Parameter (description = "Id of the category")
            @PathVariable Category c) throws CategoryNotFoundByIdException {
        List<Post> posts =postService.getAllByCategoryId(c);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("")
    @Operation(
            summary = "Ordered posts",
            description = "All posts ordered by creation date"
    )
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> posts = postService.getAll();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Post by id",
            description = "Post by ID given"
    )
    public ResponseEntity<Post> getPostById(
            @Parameter (description = "Id of the post")
            @PathVariable UUID id) throws PostNotFoundByIdException {
        Post post = postService.getById(id);
        return ResponseEntity.ok(post);
    }

}
