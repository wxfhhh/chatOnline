package com.chatOnline.controller;
import com.chatOnline.common.Result;
import com.chatOnline.domain.Group;
import com.chatOnline.domain.GroupMsg;
import com.chatOnline.domain.Member;
import com.chatOnline.domain.dto.GroupMsgQuery;
import com.chatOnline.domain.dto.UserSearch;
import com.chatOnline.service.GroupMsgService;
import com.chatOnline.service.GroupService;
import com.chatOnline.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class GroupController {
    @Autowired
    public GroupService groupService;

    @Autowired
    public GroupMsgService groupMsgService;

    @Autowired
    public MemberService memberService;

    /**
     * 获取我的群聊
     * @param map
     * @return
     */
    @PostMapping("/get_my_group")
    public Result getMyGroup(@RequestBody Map map){
        String id = (String) map.get("id");
        //获取群聊id
        List<String> groupIds = groupService.getMyGroup(id);
        List<Group> groups=new ArrayList<>();
        for (String groupId : groupIds) {
            Group byId = groupService.getById(groupId);
            groups.add(byId);
        }

        return Result.succeed(groups);
    }

    /**
     * 获取我的群聊消息
     */
    @PostMapping("/get_group_messages")
    public Result getMyGroupMsg(@RequestBody Map map){
        //获取group id
        String id = (String) map.get("id");
        //根据group id 查找该群聊里所以用户发送的消息 关联用户表
        //返回所在群聊的所以消息,用户名,用户头像
        List<GroupMsgQuery> messages = groupMsgService.getByGroupId(id);
        return Result.succeed(messages);
    }

    /**
     * 发送群聊消息 保存一次 groupmsg 数据
     */
    @PostMapping("/send_group_message")
    public Result sendGroupMsg(@RequestBody Map map){
        String group_id = (String) map.get("group_id");
        String member_id = (String) map.get("member_id");
        String content = (String) map.get("content");
        String time = (String) map.get("time");

        GroupMsg msg = new GroupMsg(null,Long.valueOf(group_id),Long.valueOf(member_id),content,time);
        groupMsgService.save(msg);
        return Result.succeed("发送成功!");
    }

    /**
     * 获取群成员
     */
    @PostMapping("/get_group_members")
    public Result getGroupMembers(@RequestBody Map map){
        String group_id = (String) map.get("id");

        List<UserSearch> members = memberService.getByGroupId(group_id);

        return Result.succeed(members);
    }

    /**
     * 搜索群聊
     */
    @PostMapping("/search_group")
    public Result search_group(@RequestBody Map<String,Object> map){
        String keyword = (String) map.get("keyword");
        List<Group> groups = groupService.getByKeyword(keyword);
        return Result.succeed(groups);
    }

    /**
     *创建群聊
     */
    @PostMapping("/create_group")
    public Result getInfo(@RequestBody Map map){
        String url = (String) map.get("url");
        String name = (String) map.get("name");
        String owner_id = (String) map.get("id");

        Group group = new Group(null,name,Long.valueOf(owner_id),new Date().toString(),url);
        groupService.save(group);
        //此时group已经存储了id
        Long id = group.getId();
        //把群主插入到群成员中
        Member member = new Member(null,id,Long.valueOf(owner_id),new Date().toString());
        memberService.save(member);

        return Result.succeed("创建成功!");
    }

    /**
     *修改群聊信息
     */
    @PostMapping("/update_group")
    public Result update_group(@RequestBody Map map){
        String url = (String) map.get("url");
        String name = (String) map.get("name");
        String id = (String) map.get("id");

        Group group = new Group(Long.valueOf(id),name,null,null,url);
        groupService.updateById(group);
        return Result.succeed("修改成功!");
    }


    /**
     * 获邀请好友入群
     */
    @PostMapping("/invite_friend")
    public Result invite_friend(@RequestBody Map<String,Object> map){
        String group_id = (String) map.get("group_id");
        String member_id = (String) map.get("member_id");

        //先判断好友是不是已经在群里了
        Member member = new Member(null,Long.valueOf(group_id),Long.valueOf(member_id),null);
        Member m = memberService.getByIds(member);
        if(m!=null)
            return Result.Error(100,"该好友已经在这个群里面了！");
        //插入一条数据
        member.setTime(new Date().toString());
        memberService.save(member);
        return Result.succeed("邀请成功!");
    }

    /**
     * 清除群聊天记录
     */
    @PostMapping("/clear_group_messages")
    public Result clear_group_messages(@RequestBody Map<String,Object> map){
        String id = (String) map.get("id");
        groupMsgService.removeByGroupId(id);
        return Result.succeed("清除成功!");
    }

    /**
     * 解散群聊
     */
    @PostMapping("/delete_group")
    public Result delete_group(@RequestBody Map map){
        //群聊 group id
        String id = (String) map.get("id");
        //清除群聊的聊天记录
        groupMsgService.removeByGroupId(id);
        //删除所有群成员
        memberService.removeByGroupId(id);
        //删除群聊
        groupService.removeById(id);

        return Result.succeed("解散成功!");
    }

    /**
     * 退出群聊,或踢出群聊
     */
    @PostMapping({"/quit_group","/tick_out"})
    public Result quit_group(@RequestBody Map map){
        String group_id = (String) map.get("group_id");
        String member_id = (String) map.get("member_id");

        if(groupService.getById(Long.valueOf(group_id)).getOwner_id()==Long.valueOf(member_id))
            return Result.Error(100,"群主不能退出!");

        Member member = new Member(null,Long.valueOf(group_id),Long.valueOf(member_id),null);

        memberService.removeMember(member);

        return Result.succeed("操作成功!");
    }

    /**
     * 获取成员id
     */
    @PostMapping("/get_members_id")
    public Result get_members_id(@RequestBody Map<String,Object> map){
        String group_id = (String) map.get("group_id");

        List<String> ids = memberService.getMembersId(group_id);

        for (int i = 0; i < ids.size(); i++) {
            System.out.println("get_member_ids:"+ids.get(i));
        }
        return Result.succeed(ids);
    }

}
