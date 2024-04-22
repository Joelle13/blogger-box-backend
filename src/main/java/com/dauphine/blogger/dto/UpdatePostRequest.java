package com.dauphine.blogger.dto;

import com.dauphine.blogger.models.Category;

import java.util.Date;
import java.util.UUID;

public class UpdatePostRequest {
    private String title;
    private String content;
    private Category category;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
