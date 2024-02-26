package com.chatOnline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chatOnline.domain.Lobby;
import com.chatOnline.mapper.LobbyMapper;
import com.chatOnline.service.LobbyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LobbyServiceImpl extends ServiceImpl<LobbyMapper, Lobby> implements LobbyService {
    @Override
    public List<Lobby> getAll() {
        LambdaQueryWrapper<Lobby> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Lobby::getId);
        return super.list(wrapper);
    }
}
