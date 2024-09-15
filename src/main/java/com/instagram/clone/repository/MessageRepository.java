package com.instagram.clone.repository;

import com.instagram.clone.entity.Message;
import com.instagram.clone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderAndRecipient(User sender, User recipient);
}

