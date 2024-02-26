package com.chatOnline.controller;

import com.chatOnline.common.Result;
import com.chatOnline.domain.Friend;
import com.chatOnline.domain.Notice;
import com.chatOnline.domain.dto.NoticeQuery;
import com.chatOnline.service.FriendService;
import com.chatOnline.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @Autowired
    FriendService friendService;

    /**
     * 获取系统消息，加好友信息
     */
    @PostMapping("/get_notice")
    public Result getNotice(@RequestBody Map map){
        String id = (String) map.get("id");
        List<NoticeQuery> notices = noticeService.getByFromOrTo(id);
        return Result.succeed(notices);
    }

    /**
     * 发送添加好友申请
     */
    @PostMapping("/post_add_apply")
    public Result addFriend(@RequestBody Map map){
        String from = (String) map.get("from");
        String to = (String) map.get("to");
        String msg = (String) map.get("msg");

        Notice notice= new Notice();
        notice.setFrom(Long.valueOf(from));
        notice.setTo(Long.valueOf(to));
        notice.setMsg(msg);
        notice.setType(0);
        Calendar now = Calendar.getInstance();
        notice.setTime(now.get(Calendar.MONTH)+1+"."+now.get(Calendar.DAY_OF_MONTH));

        if(from.equals(to))
            return Result.Error(100,"你不能加自己为好友！");

        Friend f = new Friend();
        f.setMe(Long.valueOf(from));
        f.setFriend(Long.valueOf(to));

        Friend friend = friendService.getByMeAndFriend(f);
        if(friend!=null)
            return Result.Error(100,"他已经是你的好友了！");

        if(noticeService.getByFromAndTo(notice)!=null)
            return Result.Error(100,"你已经向ta发送过请求了！");

        noticeService.save(notice);

        return Result.succeed("添加成功!");
    }

    /**
     * 同意好友申请
     */
    @PostMapping("/agree_apply")
    public Result agreeApply(@RequestBody Map map) {
        String userId = (String) map.get("userId");
        String noticeId = (String) map.get("noticeId");


        if(userId.equals(""))
            return Result.Error(100,"没有用户登录！");

        Notice notice = noticeService.getById(noticeId);
        if(notice==null)
            return Result.Error(100,"没有找到数据！");
        //friend1 发申请的人 friend 收到申请的人
        Friend friend1 = new Friend();
        Friend friend2 = new Friend();
        friend1.setMe(Long.valueOf(notice.getFrom()));
        friend1.setFriend(Long.valueOf(notice.getTo()));
        friend2.setMe(Long.valueOf(notice.getTo()));
        friend2.setFriend(Long.valueOf(notice.getFrom()));

        if(friendService.getByMeAndFriend(friend1)!=null){
            noticeService.removeById(noticeId);
            return Result.Error(100,"对方已经是您的好友了！");
        }
        //添加好友
        friendService.save(friend1);
        friendService.save(friend2);
        //删除好友申请:已经添加成功!
        noticeService.removeById(noticeId);

        Notice notice1 = new Notice();
        notice1.setType(1);
        notice1.setFrom(notice.getTo());
        notice1.setTo(notice.getFrom());
        Calendar now = Calendar.getInstance();
        notice1.setTime(now.get(Calendar.MONTH)+1+"."+now.get(Calendar.DAY_OF_MONTH));
        //发送对方同意添加好友的信息
        noticeService.save(notice1);
        return Result.succeed("同意好友申请!");
    }


    /**
     * 删除通知
     */
    @PostMapping("/delete_notice")
    public Result deleteNotice(@RequestBody Map map){
        String userId = (String) map.get("userId");
        String noticeId = (String) map.get("noticeId");

        if(userId.equals(""))
            return Result.Error(100,"没有用户登录！");

        Notice notice = noticeService.getById(noticeId);

        if(notice==null)
            return Result.Error(100,"没有找到数据！");

        if(notice.getType()==1||notice.getType()==2||notice.getType()==3){
            noticeService.removeById(notice.getId().toString());
            return Result.succeed("删除成功!");
        }
        //自己撤回
        if(notice.getType()==0 && notice.getFrom().equals(userId)){
            noticeService.removeById(notice.getId().toString());
            return Result.succeed("删除成功!");
        }
        // 删除验证信息
        Calendar now = Calendar.getInstance();
        Notice notice1 = new Notice();
        notice1.setTime(now.get(Calendar.MONTH)+1+"."+now.get(Calendar.DAY_OF_MONTH));
        notice1.setFrom(notice.getTo());
        notice1.setTo(notice.getFrom());
        notice1.setType(2);
        noticeService.removeById(notice.getId().toString());
        noticeService.save(notice1);
        return Result.succeed("删除验证信息成功!");
    }
}
