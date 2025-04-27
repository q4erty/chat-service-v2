package com.practice.websocketchat;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    public MessageController(MessageService messageService, SimpMessagingTemplate messagingTemplate) {
        this.messageService = messageService;
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping
    public ResponseEntity<Message> sendMessage(
            @RequestParam String sender,
            @RequestParam String content,
            @RequestParam(required = false) MultipartFile file) throws IOException {

        Message savedMessage = messageService.saveMessage(sender, content, file);
        messagingTemplate.convertAndSend("/topic/messages", savedMessage);
        return ResponseEntity.ok(savedMessage);
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }
}
