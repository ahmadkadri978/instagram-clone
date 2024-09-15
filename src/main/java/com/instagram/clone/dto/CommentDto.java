package com.instagram.clone.dto;

public class CommentDto {
    private Long id;
    private String text;
    private UserDto user;  // استخدام DTO خاص بالمستخدم لتجنب إظهار كلمة المرور
    private PostDto post;  // استخدام DTO خاص بالمنشور لتجنب عرض بيانات غير ضرورية

    // Constructors, getters and setters

    public CommentDto(Long id, String text, UserDto user, PostDto post) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.post = post;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public PostDto getPost() {
        return post;
    }

    public void setPost(PostDto post) {
        this.post = post;
    }
}

