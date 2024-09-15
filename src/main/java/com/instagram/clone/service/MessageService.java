package com.instagram.clone.service;

import com.instagram.clone.entity.Message;
import com.instagram.clone.entity.User;
import com.instagram.clone.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getConversation(User sender, User recipient) {
        return messageRepository.findBySenderAndRecipient(sender, recipient);
    }

    public void sendMessage(User sender, User recipient, String content) {
        Message message = new Message();
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setContent(content);
        message.setTimestamp(new Date());
        messageRepository.save(message);
    }
}

