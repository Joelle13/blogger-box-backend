package com.dauphine.blogger.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table (name = "category")
public class Category {

    @Id
    @Column(name="id")
    private UUID uuid;

    @Column(name = "name")
    private String name;

    public Category( String name) {
        this.uuid = UUID.randomUUID();
        this.name = name;
    }

    public Category() {

    }

    public UUID getId() {
        return uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
