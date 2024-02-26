package com.chatOnline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chatOnline.domain.Lobby;

import java.util.List;

public interface LobbyService extends IService<Lobby> {

    List<Lobby> getAll();
}
