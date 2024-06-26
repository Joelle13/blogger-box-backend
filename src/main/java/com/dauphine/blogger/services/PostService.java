package com.dauphine.blogger.services;

import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.exceptions.PostNotFoundByIdException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.dto.CreationPostRequest;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllByCategoryId(Category category) throws CategoryNotFoundByIdException;
    List<Post> getAll();
    Post getById(UUID id) throws PostNotFoundByIdException;
    Post create(CreationPostRequest post) throws CategoryNotFoundByIdException;
    Post update(UUID id, String title, String content) throws PostNotFoundByIdException;
    boolean deleteById(UUID id) throws PostNotFoundByIdException;
}
