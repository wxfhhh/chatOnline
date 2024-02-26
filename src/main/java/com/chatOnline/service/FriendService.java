package com.chatOnline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chatOnline.domain.Friend;
import com.chatOnline.domain.User;

import java.util.List;


public interface FriendService extends IService<Friend> {

    List<String> findMyFriends(String me);
    void deleteFriend(Friend friend);

    Friend getByMeAndFriend(Friend friend);

}
