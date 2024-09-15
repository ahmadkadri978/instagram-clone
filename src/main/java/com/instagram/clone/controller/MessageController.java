package com.instagram.clone.controller;

import com.instagram.clone.dto.MessageDto;
import com.instagram.clone.dto.UserDto;
import com.instagram.clone.entity.Message;
import com.instagram.clone.entity.User;
import com.instagram.clone.security.CustomUserDetails;
import com.instagram.clone.service.MessageService;
import com.instagram.clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    @Autowired
    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping("/conversation")
    public ResponseEntity<List<MessageDto>> getConversation(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam Long recipientId) {
        User sender = customUserDetails.getUser();
        User recipient = userService.findById(recipientId);
        List<Message> conversation = messageService.getConversation(sender, recipient);
        List<MessageDto> messageDTOs = conversation.stream().map(message -> {
            UserDto senderDTO = new UserDto(message.getSender().getId(), message.getSender().getUsername(), message.getSender().getEmail());
            UserDto recipientDTO = new UserDto(message.getRecipient().getId(), message.getRecipient().getUsername(), message.getRecipient().getEmail());
            return new MessageDto(message.getId(), senderDTO, recipientDTO, message.getContent(), message.getTimestamp());
        }).collect(Collectors.toList());
        return ResponseEntity.ok(messageDTOs);
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam Long recipientId, @RequestParam String content) {
        User sender = customUserDetails.getUser();
        User recipient = userService.findById(recipientId);
        messageService.sendMessage(sender, recipient, content);
        return ResponseEntity.ok().build();
    }
}
