package com.chatOnline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chatOnline.domain.User;
import com.chatOnline.domain.dto.UserSearch;

import java.util.List;


public interface UserService extends IService<User> {
    public User getOne(String email);

    public List<UserSearch> getFriendList(String id);

    List<UserSearch> getByKeyword(String keyword);

    UserSearch getOnline(String id);

}
