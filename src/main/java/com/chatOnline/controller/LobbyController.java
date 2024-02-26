package com.chatOnline.controller;

import com.chatOnline.common.Result;
import com.chatOnline.domain.Lobby;
import com.chatOnline.service.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 大厅 公共聊天室
 */
@RestController
public class LobbyController {

    @Autowired
    LobbyService lobbyService;
    /**
     * 获取大厅聊天记录
     */
    @PostMapping("/get_lobby_messages")
    public Result getLobbyMessage(){
        return Result.succeed(lobbyService.getAll());
    }

    /**
     * 插入一条聊天室的记录
     */
    @PostMapping("/send_lobby_message")
    public Result sendLobby(@RequestBody Map map){
        String name = (String) map.get("name");
        String url = (String) map.get("url");
        String time = (String) map.get("time");
        String content = (String) map.get("content");

        if(name.equals(""))
            return Result.Error(100,"没有用户id/name");

        Lobby lobby = new Lobby(null,name,url,time,content);
        lobbyService.save(lobby);
        return Result.succeed(lobby);
    }
}
