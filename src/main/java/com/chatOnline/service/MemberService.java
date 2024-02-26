package com.chatOnline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chatOnline.domain.Member;
import com.chatOnline.domain.dto.UserSearch;

import java.util.List;

public interface MemberService extends IService<Member> {
    List<UserSearch> getByGroupId(String id);

    void removeByGroupId(String id);

    Member getByIds(Member member);

    void removeMember(Member member);

    List<String> getMembersId(String id);
}
