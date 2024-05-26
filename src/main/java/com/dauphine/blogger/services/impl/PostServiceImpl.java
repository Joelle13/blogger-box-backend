package com.dauphine.blogger.services.impl;

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
    private final List<Post> temporaryPosts;

    private final PostRepository repository;
    private final CategoryService categoryService;
    public PostServiceImpl(PostRepository repository, CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
        this.temporaryPosts = new ArrayList<>();
        Category c = new Category(UUID.randomUUID(), "my first category");
        temporaryPosts.add(new Post(UUID.randomUUID(), "First post","This is my first post, hello",new Date(),c));
        temporaryPosts.add(new Post(UUID.randomUUID(), "Second post","This is my second post, hello",new Date(),c));
        temporaryPosts.add(new Post(UUID.randomUUID(), "Third post","This is my third post, hello",new Date(),c));
    }
    @Override
    public List<Post> getAllByCategoryId(Category category) {
        return temporaryPosts.stream()
                .filter(post -> category.equals(post.getCategory()))
                .toList();
    }

    @Override
    public List<Post> getAll() {
        return repository.findAll();
    }

    @Override
    public Post getById(UUID id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public Post create(CreationPostRequest postRequest) {
        Post p = new Post(UUID.randomUUID(), postRequest.getTitle(),postRequest.getContent(),new Date(),
                categoryService.getById(postRequest.getCategoryId()));
        return repository.save(p);
    }

    @Override
    public Post update(UUID id, String title, String content) {
        Post p = temporaryPosts.stream()
                .filter(post -> id.equals(post.getCategory()))
                .findFirst()
                .orElse(null);
        if(p!=null){
            p.setTitle(title);
            p.setContent(content);
        }
        return repository.save(p);
    }

    @Override
    public boolean deleteById(UUID id) {
        repository.deleteById(id);
        return true;
    }
}
