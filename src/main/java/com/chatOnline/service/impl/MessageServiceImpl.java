package com.chatOnline.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chatOnline.domain.Friend;
import com.chatOnline.domain.Message;
import com.chatOnline.mapper.FriendMapper;
import com.chatOnline.mapper.MessageMapper;
import com.chatOnline.service.FriendService;
import com.chatOnline.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
    @Autowired
    public MessageMapper messageMapper;
    @Override
    public List<Message> getMessages(Friend friend) {
        return messageMapper.getMessages(friend);
    }

    @Override
    public void deleteByMeAndFriend(Friend friend) {
        messageMapper.deleteByMeAndFriend(friend);
    }
}
