package com.yurticikargo.gib.repository.entity;

import com.google.type.DateTime;
import com.yurticikargo.gib.repository.enums.MessageType;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "messages")
public class ChatMessage {

    private String content;
    private String sender;
    private String receiver;
    private MessageType messageType;
    private Long dateTime;
}