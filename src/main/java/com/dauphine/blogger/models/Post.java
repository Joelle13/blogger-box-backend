package com.dauphine.blogger.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="post")
public class Post {

    @Id
    @Column(name = "id")
    private UUID uuid;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "created_date")
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;


    public Post(UUID uuid, String title, String content,Date date, Category c) {
        this.uuid = uuid;
        this.title = title;
        this.content = content;
        createdDate = date;
        category = c;
    }

    public Post() {

    }

    public Category getCategory() {
        return this.category;
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
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
