package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.dto.CreationPostRequest;
import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.exceptions.PostNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.PostRepository;
import com.dauphine.blogger.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostServiceImplTest {

    private PostRepository postRepository;
    private CategoryService categoryService;
    private PostServiceImpl postService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        categoryService = mock(CategoryService.class);
        postService = new PostServiceImpl(postRepository, categoryService);
    }

    @Test
    void shouldReturnAllPostsByCategoryId() throws CategoryNotFoundByIdException {
        UUID categoryId = UUID.randomUUID();
        Category category = new Category("Category");
        List<Post> expectedPosts = List.of(new Post(UUID.randomUUID(), "Post1", "Content1", new Date(), category),
                new Post(UUID.randomUUID(), "Post2", "Content2", new Date(), category));
        when(categoryService.getById(categoryId)).thenReturn(category);
        when(postRepository.getAllByCategory(category)).thenReturn(expectedPosts);

        List<Post> actualPosts = postService.getAllByCategoryId(category);

        assertEquals(expectedPosts.size(), actualPosts.size());
        verify(postRepository).getAllByCategory(category);
    }

    @Test
    void shouldReturnAllPosts() {
        List<Post> expectedPosts = List.of(new Post(UUID.randomUUID(), "Post1", "Content1", new Date(), null),
                new Post(UUID.randomUUID(), "Post2", "Content2", new Date(), null));
        when(postRepository.findAll()).thenReturn(expectedPosts);

        List<Post> actualPosts = postService.getAll();

        assertEquals(expectedPosts.size(), actualPosts.size());
        verify(postRepository).findAll();
    }

    @Test
    void shouldReturnPostWhenGetById() throws PostNotFoundByIdException {
        UUID id = UUID.randomUUID();
        Post expectedPost = new Post(id, "Post", "Content", new Date(), null);
        when(postRepository.findById(id)).thenReturn(Optional.of(expectedPost));

        Post actualPost = postService.getById(id);

        assertEquals(expectedPost, actualPost);
    }

    @Test
    void shouldThrowExceptionWhenPostNotFoundById() {
        UUID id = UUID.randomUUID();
        when(postRepository.findById(id)).thenReturn(Optional.empty());

        PostNotFoundByIdException exception = assertThrows(
                PostNotFoundByIdException.class,
                () -> postService.getById(id)
        );

        assertEquals("Post not found !!", exception.getMessage());
    }

    @Test
    void shouldCreatePost() throws CategoryNotFoundByIdException {
        UUID categoryId = UUID.randomUUID();
        CreationPostRequest postRequest = new CreationPostRequest("Post", "Content", categoryId);
        Category category = new Category("Category");
        Post expectedPost = new Post(UUID.randomUUID(), postRequest.getTitle(), postRequest.getContent(), new Date(), category);
        when(categoryService.getById(categoryId)).thenReturn(category);
        when(postRepository.save(any(Post.class))).thenReturn(expectedPost);

        Post actualPost = postService.create(postRequest);

        assertEquals(expectedPost, actualPost);
    }

    @Test
    void shouldUpdatePost() throws PostNotFoundByIdException {
        UUID id = UUID.randomUUID();
        Post oldPost = new Post(id, "Old Post", "Old Content", new Date(), null);
        Post expectedPost = new Post(id, "Updated Post", "Updated Content", new Date(), null);
        when(postRepository.findById(id)).thenReturn(Optional.of(oldPost));
        when(postRepository.save(any(Post.class))).thenReturn(expectedPost);

        Post actualPost = postService.update(id, "Updated Post", "Updated Content");

        assertEquals(expectedPost, actualPost);
    }

    @Test
    void shouldThrowPostNotFoundExceptionWhenUpdate() {
        UUID id = UUID.randomUUID();
        when(postRepository.findById(id)).thenReturn(Optional.empty());

        PostNotFoundByIdException thrownException = assertThrows(
                PostNotFoundByIdException.class,
                () -> postService.update(id, "Updated Post", "Updated Content")
        );

        assertEquals("Post not found !!", thrownException.getMessage());
    }

    @Test
    void shouldDeleteWhenDeleteById() throws PostNotFoundByIdException {
        UUID id = UUID.randomUUID();
        Post post = new Post(id, "Post", "Content", new Date(), null);
        when(postRepository.findById(id)).thenReturn(Optional.of(post));
        doNothing().when(postRepository).deleteById(id);

        boolean result = postService.deleteById(id);

        verify(postRepository).deleteById(id);
        assertTrue(result);
    }

    @Test
    void shouldReturnExceptionWhenDeleteByIdAndPostNotFound() throws PostNotFoundByIdException {
        UUID id = UUID.randomUUID();
        when(postRepository.findById(id)).thenReturn(Optional.empty());

        PostNotFoundByIdException thrownException = assertThrows(
                PostNotFoundByIdException.class,
                () -> postService.deleteById(id)
        );

        assertEquals("Post not found !!", thrownException.getMessage());
    }
}
