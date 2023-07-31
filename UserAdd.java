package com.yurticikargo.gib.repository.entity;

import com.yurticikargo.gib.repository.enums.MessageType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAdd {

    private String content;
    private String sender;
    private MessageType messageType;
}