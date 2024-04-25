package com.dauphine.blogger.models;

import java.util.Date;
import java.util.UUID;

public class Post {
    private UUID uuid;
    private String title;
    private String content;
    private Date createdDate;
    private UUID idCategory;


    public Post(UUID uuid, String title, String content,Date date, UUID catId) {
        this.uuid = uuid;
        this.title = title;
        this.content = content;
        createdDate = date;
        idCategory = catId;
    }

    public UUID getCatId() {
        return this.idCategory;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UUID getId() {
        return this.uuid;
    }
}
