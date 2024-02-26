package com.chatOnline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chatOnline.domain.Member;
import com.chatOnline.domain.dto.UserSearch;
import com.chatOnline.mapper.MemberMapper;
import com.chatOnline.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
    @Autowired
    MemberMapper memberMapper;
    @Override
    public List<UserSearch> getByGroupId(String id) {
        return memberMapper.getByGroupId(id);
    }

    @Override
    public void removeByGroupId(String id) {
        LambdaQueryWrapper<Member> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Member::getGroup_id,Long.valueOf(id));
        super.remove(wrapper);
    }

    @Override
    public Member getByIds(Member member) {
        LambdaQueryWrapper<Member> w = new LambdaQueryWrapper<>();
        w.eq(Member::getGroup_id,member.getGroup_id()).eq(Member::getMember_id,member.getMember_id());
        return super.getOne(w);
    }

    @Override
    public void removeMember(Member member) {
        LambdaQueryWrapper<Member> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Member::getGroup_id,member.getGroup_id())
                .eq(Member::getMember_id,member.getMember_id());
        super.remove(wrapper);
    }

    @Override
    public List<String> getMembersId(String id) {
        LambdaQueryWrapper<Member> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Member::getGroup_id,id);
        List<Member> list = super.list(wrapper);
        List<String> ids=new ArrayList<>();
        for (Member member:list) {
            ids.add(member.getMember_id().toString());
        }
        return ids;
    }
}
