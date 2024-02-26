package com.chatOnline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chatOnline.domain.User;
import com.chatOnline.domain.dto.UserSearch;
import com.chatOnline.mapper.UserMapper;
import com.chatOnline.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Autowired
    public UserMapper userMapper;

    @Override
    public User getOne(String email) {
        //数据库获取数据
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail,email);
        User one = super.getOne(wrapper);
        return one;
    }

    @Override
    public List<UserSearch> getFriendList(String id) {
        return userMapper.getFriendList(id);
    }

    @Override
    public List<UserSearch> getByKeyword(String keyword) {
        return userMapper.getByKeyword(keyword);
    }

    @Override
    public UserSearch getOnline(String id) {
        return userMapper.getOnline(id);
    }

}
