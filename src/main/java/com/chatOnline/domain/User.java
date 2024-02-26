package com.chatOnline.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String email;
    private String nickname;
    private String password;
    private String birthday = new Date().toString();//默认注册日
    private String url = "/images/normal.png";//默认头像
    private String phone;
    private String signature;
    private String sex = null;//默认性别未知
    private String register_time = new Date().toString();//默认注册日
}
