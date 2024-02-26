package com.chatOnline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chatOnline.domain.Friend;
import com.chatOnline.mapper.FriendMapper;
import com.chatOnline.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendService {
//    @Autowired
//    FriendMapper friendMapper;
    @Override
    public List<String> findMyFriends(String me) {
        LambdaQueryWrapper<Friend> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Friend::getMe,me);
        List<Friend> list = this.list(wrapper);
        List<String> f=new LinkedList<>();
        for (Friend friend :
                list) {
            f.add(friend.getFriend().toString());
        }
        return f;
    }

    @Override
    public void deleteFriend(Friend friend) {
        LambdaQueryWrapper<Friend> wrapper = new LambdaQueryWrapper<>();
        wrapper.and(wp->wp.eq(Friend::getMe,friend.getMe())
                .eq(Friend::getFriend,friend.getFriend()))
                .or(wp->wp.eq(Friend::getMe,friend.getFriend())
                        .eq(Friend::getFriend,friend.getMe()));
        super.removeById(wrapper);
//delete from friend
//where (me=#{me} and friend=#{friend}) or (me=#{friend} and friend=#{me})

    }

    @Override
    public Friend getByMeAndFriend(Friend friend) {
        LambdaQueryWrapper<Friend> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Friend::getMe,friend.getMe())
                .eq(Friend::getFriend,friend.getFriend());
        return super.getOne(wrapper);
    }
}
