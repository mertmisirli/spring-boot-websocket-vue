package com.yurticikargo.gib.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddMessageRequestDto {
    private String sender;
    private String receiver;
    private String message;
    private Long sentTime;

}
