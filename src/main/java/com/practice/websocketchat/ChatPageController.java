package com.practice.websocketchat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatPageController {

    private final MessageService messageService;

    public ChatPageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String chatPage(Model model) {
        model.addAttribute("messages", messageService.getAllMessages());
        return "chat";
    }
}
