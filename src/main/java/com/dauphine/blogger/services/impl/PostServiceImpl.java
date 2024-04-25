package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {
    private final List<Post> temporaryPosts;
    public PostServiceImpl() {
        this.temporaryPosts = new ArrayList<>();
        Category c = new Category(UUID.randomUUID(), "my first category");
        temporaryPosts.add(new Post(UUID.randomUUID(), "First post","This is my first post, hello",new Date(),c.getId()));
        temporaryPosts.add(new Post(UUID.randomUUID(), "Second post","This is my second post, hello",new Date(),c.getId()));
        temporaryPosts.add(new Post(UUID.randomUUID(), "Third post","This is my third post, hello",new Date(),c.getId()));
    }
    @Override
    public List<Post> getAllByCategoryId(UUID categoryId) {
        return temporaryPosts.stream()
                .filter(post -> categoryId.equals(post.getCatId()))
                .toList();
    }

    @Override
    public List<Post> getAll() {
        return temporaryPosts;
    }

    @Override
    public Post getById(UUID id) {
        return temporaryPosts.stream()
                .filter(post -> id.equals(post.getCatId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Post create(String title, String content, UUID categoryId) {
        Post p = new Post(UUID.randomUUID(), title,content,new Date(),categoryId);
        temporaryPosts.add(p);
        return p;
    }

    @Override
    public Post update(UUID id, String title, String content) {
        Post p = temporaryPosts.stream()
                .filter(post -> id.equals(post.getCatId()))
                .findFirst()
                .orElse(null);
        if(p!=null){
            p.setTitle(title);
            p.setContent(content);
        }
        return p;
    }

    @Override
    public boolean deleteById(UUID id) {
        return temporaryPosts.removeIf(post -> id.equals(post.getId()));
    }
}
