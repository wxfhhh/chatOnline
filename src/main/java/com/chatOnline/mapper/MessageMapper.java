package com.chatOnline.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chatOnline.domain.Friend;
import com.chatOnline.domain.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    public List<Message> getMessages(Friend friend);
    public void deleteByMeAndFriend(Friend friend);
}
