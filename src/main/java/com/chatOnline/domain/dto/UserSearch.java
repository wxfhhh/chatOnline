package com.chatOnline.domain.dto;

import lombok.Data;

@Data
public class UserSearch {
    private Long id;
    private String email;
    private String nickname;
    private String url;
    private String sex;
    private String phone;
    private String signature;
    private String birthday;
}
