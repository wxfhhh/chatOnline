package com.chatOnline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chatOnline.domain.Friend;
import com.chatOnline.domain.Message;

import java.util.List;


public interface MessageService extends IService<Message> {

    public List<Message> getMessages(Friend friend);

    public void deleteByMeAndFriend(Friend friend);
}
