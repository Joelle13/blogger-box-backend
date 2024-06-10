package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.exceptions.PostNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.PostRepository;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.PostService;
import org.springframework.stereotype.Service;
import com.dauphine.blogger.dto.CreationPostRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CategoryService categoryService;
    public PostServiceImpl(PostRepository repository, CategoryService categoryService) {
        this.postRepository = repository;
        this.categoryService = categoryService;
        }
    @Override
    public List<Post> getAllByCategoryId(Category category) throws CategoryNotFoundByIdException {
        categoryService.getById(category.getId());
        return postRepository.getAllByCategory(category);
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Post getById(UUID id) throws PostNotFoundByIdException {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundByIdException());
    }

    @Override
    public Post create(CreationPostRequest postRequest) throws CategoryNotFoundByIdException {
        Post p = new Post(UUID.randomUUID(), postRequest.getTitle(),postRequest.getContent(),new Date(),
                categoryService.getById(postRequest.getCategoryId()));
        return postRepository.save(p);
    }

    @Override
    public Post update(UUID id, String title, String content) throws PostNotFoundByIdException {
        Post p = getById(id);
        p.setTitle(title);
        p.setContent(content);
        return postRepository.save(p);
    }

    @Override
    public boolean deleteById(UUID id) throws PostNotFoundByIdException {
        getById(id);
        postRepository.deleteById(id);
        return true;
    }
}
