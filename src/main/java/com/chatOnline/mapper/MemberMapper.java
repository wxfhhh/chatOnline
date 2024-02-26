package com.chatOnline.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chatOnline.domain.Member;
import com.chatOnline.domain.dto.UserSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {
    List<UserSearch> getByGroupId(String id);

    Member getByIds(Member member);
}
