package com.practice.websocketchat;

import com.practice.websocketchat.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final FileStorageService fileStorageService;

    public MessageService(MessageRepository messageRepository, FileStorageService fileStorageService) {
        this.messageRepository = messageRepository;
        this.fileStorageService = fileStorageService;
    }

    public Message saveMessage(String sender, String content, MultipartFile file) throws IOException {
        Message message = new Message();
        message.setSender(sender);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());

        if (file != null && !file.isEmpty()) {
            FileMetadata metadata = fileStorageService.storeFile(file);
            message.setAttachment(metadata);
        }

        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
