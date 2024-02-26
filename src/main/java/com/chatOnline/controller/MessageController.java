package com.chatOnline.controller;

import com.chatOnline.common.Result;
import com.chatOnline.domain.Friend;
import com.chatOnline.domain.Message;
import com.chatOnline.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MessageController {
    @Autowired
    public MessageService messageService;

    /**
     * 获取聊天信息
     */
    @PostMapping("/get_message")
    public Result getMessage(@RequestBody Map map){
        String me = (String) map.get("me");
        String friend =(String)map.get("friend");

        if(me.equals("")||friend.equals(""))
            return Result.Error(100,"获取信息出错!");

        Friend f = new Friend(null,Long.valueOf(me),Long.valueOf(friend));

        List<Message> messages = messageService.getMessages(f);

        return Result.succeed(messages);
    }

    /**
     * 保存聊天记录
     */
    @PostMapping("/send_message")
    public Result sendMessage(@RequestBody Map map){
        String from = (String)map.get("from");
        String to = (String) map.get("to");
        String content = (String) map.get("content");

        if(from.equals("")||to.equals(""))
            return Result.Error(100,"获取信息出错！");

        Message message = new Message(null,Long.valueOf(from),Long.valueOf(to),content);
        messageService.save(message);

        return Result.succeed("保存成功!");
    }

    /**
     * 清除聊天记录
     */
    @PostMapping("/clear_record")
    public Result clearMessage(@RequestBody Map<String,Object> map){
        String me = (String) map.get("me");
        String friend = (String) map.get("friend");

        if(me.equals("")||friend.equals(""))
            return Result.Error(100,"获取信息出错！");

        Friend f = new Friend(null,Long.valueOf(me),Long.valueOf(friend));
        messageService.deleteByMeAndFriend(f);

        return Result.succeed("清除成功!");
    }
}
