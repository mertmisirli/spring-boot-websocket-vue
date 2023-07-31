package com.yurticikargo.gib.controller;

import com.yurticikargo.gib.dto.request.AddMessageRequestDto;
import com.yurticikargo.gib.repository.entity.ChatMessage;
import com.yurticikargo.gib.repository.entity.UserAdd;
import com.yurticikargo.gib.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
@Slf4j
@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public AddMessageRequestDto sendMessage(@Payload AddMessageRequestDto chatMessage) {

        System.out.println(
                "Sender : " + chatMessage.getSender() +
                " Chat Message Content : " + chatMessage.getMessage() +
                " Receiver : " + chatMessage.getReceiver()
        );
        chatService.saveMessage(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public UserAdd addUser(UserAdd userAdd, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", userAdd.getSender());
        return userAdd;
    }
}