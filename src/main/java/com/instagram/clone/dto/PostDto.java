package com.instagram.clone.dto;

public class PostDto {
    private Long id;
    private String caption;
    private String imageUrl;

    // Constructors, getters and setters

    public PostDto(Long id, String caption, String imageUrl) {
        this.id = id;
        this.caption = caption;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
