package com.dauphine.blogger.dto;

import com.dauphine.blogger.models.Category;

import java.util.Date;
import java.util.UUID;

public class UpdatePostRequest {
    private String title;
    private String content;
    private UUID postId;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public UUID getPostId() {
        return postId;
    }
}
