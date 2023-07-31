package com.yurticikargo.gib.service;

import com.yurticikargo.gib.dto.request.AddMessageRequestDto;
import com.yurticikargo.gib.repository.MessageRepository;
import com.yurticikargo.gib.repository.entity.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final MessageRepository messageRepository;

    public ChatMessage saveMessage(AddMessageRequestDto requestDto){
        return messageRepository.save(ChatMessage.builder()
                        .sender(requestDto.getSender())
                        .receiver(requestDto.getReceiver())
                        .content(requestDto.getMessage())
                        .dateTime(requestDto.getSentTime())
                .build());
    }
}
