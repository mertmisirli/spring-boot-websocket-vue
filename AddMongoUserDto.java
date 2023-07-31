package com.yurticikargo.gib.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddMongoUserDto {

    private String userName;
    private String userSurname;

}
