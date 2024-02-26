package com.chatOnline.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chatOnline.domain.GroupMsg;
import com.chatOnline.domain.dto.GroupMsgQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupMsgMapper extends BaseMapper<GroupMsg> {

    List<GroupMsgQuery> getByGroupId(String id);
}
