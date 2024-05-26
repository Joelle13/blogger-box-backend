package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.dto.CreationPostRequest;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllByCategoryId(Category category);
    List<Post> getAll();
    Post getById(UUID id);
    Post create(CreationPostRequest post);
    Post update(UUID id, String title, String content);
    boolean deleteById(UUID id);
}
