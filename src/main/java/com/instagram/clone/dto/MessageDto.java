package com.instagram.clone.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class MessageDto {
    private Long id;
    private UserDto sender;
    private UserDto recipient;
    private String content;
    private Date timestamp;

    // Constructors, Getters and Setters


    public MessageDto() {
    }

    public MessageDto(Long id, UserDto sender, UserDto recipient, String content, Date timestamp) {
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.timestamp = timestamp;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getSender() {
        return sender;
    }

    public void setSender(UserDto sender) {
        this.sender = sender;
    }

    public UserDto getRecipient() {
        return recipient;
    }

    public void setRecipient(UserDto recipient) {
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
