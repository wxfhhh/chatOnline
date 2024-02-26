package com.chatOnline.controller;

import com.chatOnline.common.Result;
import com.chatOnline.domain.Friend;
import com.chatOnline.domain.Notice;
import com.chatOnline.domain.dto.UserSearch;
import com.chatOnline.service.FriendService;
import com.chatOnline.service.MessageService;
import com.chatOnline.service.NoticeService;
import com.chatOnline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RestController
public class FriendController {
    @Autowired
    public FriendService friendService;
    @Autowired
    public UserService userService;

    @Autowired
    public MessageService messageService;

    @Autowired
    public NoticeService noticeService;

    /**
     * 获取好友列表
     */
    @PostMapping("/get_friend_list")
    public Result getFriend(@RequestBody Map map){
        String id = (String) map.get("id");
        List<UserSearch> friends = userService.getFriendList(id);
        return Result.succeed(friends);
    }

    /**
     * 查找朋友
     * @param map
     * @return
     */
    @PostMapping("/search_user")
    public Result searchUser(@RequestBody Map map){
        String keyword = (String) map.get("keyword");
        String id = (String) map.get("id");
        if(keyword.equals("")){
            Result.Error(100,"没有关键字!");
        }
        //根据关键词 email 查找用户
        List<UserSearch> users = userService.getByKeyword(keyword);

        //除去已经是好友的用户 根据用户id查找朋友
        List<String> friends = friendService.findMyFriends(id);

        friends.add(id);
        users.removeIf(user -> friends.contains(user.getId()));

        return Result.succeed(users);
    }
    /**
     * 添加好友
     */

    /**
     * 删除好友
     */
    @PostMapping("/delete_friend")
    public Result deleteFriend(@RequestBody Map map){

        String me = (String) map.get("me");
        String friend = (String) map.get("friend");


        if(me.equals("")||friend.equals(""))
            return Result.Error(100,"获取信息出错！");

        Friend f = new Friend(null,Long.valueOf(me),Long.valueOf(friend));
        //删除好友记录
        friendService.deleteFriend(f);
        //删除聊天信息
        messageService.deleteByMeAndFriend(f);

        Calendar now = Calendar.getInstance();
        Notice notice1 = new Notice();
        notice1.setTime(now.get(Calendar.MONTH)+1+"."+now.get(Calendar.DAY_OF_MONTH));
        notice1.setFrom(Long.valueOf(me));
        notice1.setTo(Long.valueOf(friend));
        notice1.setType(3);
        noticeService.save(notice1);

        return Result.succeed("删除成功!");
    }


}
