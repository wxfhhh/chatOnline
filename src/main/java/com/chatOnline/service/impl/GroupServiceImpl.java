package com.chatOnline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chatOnline.domain.Group;
import com.chatOnline.domain.Member;
import com.chatOnline.domain.User;
import com.chatOnline.mapper.GroupMapper;
import com.chatOnline.service.GroupService;
import com.chatOnline.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {
    @Autowired
    MemberService memberService;
    @Override
    public List<String> getMyGroup(String id) {
        LambdaQueryWrapper<Member> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Member::getMember_id,Long.parseLong(id));
        List<Member> list = memberService.list(wrapper);
        List<String> groupIds=new ArrayList<>();
        for (Member m : list) {
            groupIds.add(m.getGroup_id().toString());
        }
        return groupIds;
    }

    @Override
    public List<Group> getByKeyword(String keyword) {
        LambdaQueryWrapper<Group> wrapper=new LambdaQueryWrapper<>();
        wrapper.like(Group::getId,keyword).or().like(Group::getName,keyword);
        return super.list(wrapper);
    }
}
