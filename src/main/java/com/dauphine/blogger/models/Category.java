package com.dauphine.blogger.models;

import java.util.UUID;

public class Category {
    private UUID uuid;
    private String name;
    public Category(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }
}
