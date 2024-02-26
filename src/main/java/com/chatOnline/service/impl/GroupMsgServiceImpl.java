package com.chatOnline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chatOnline.domain.GroupMsg;
import com.chatOnline.domain.dto.GroupMsgQuery;
import com.chatOnline.mapper.GroupMsgMapper;
import com.chatOnline.service.GroupMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMsgServiceImpl extends ServiceImpl<GroupMsgMapper, GroupMsg> implements GroupMsgService {
    @Autowired
    public GroupMsgMapper groupMsgMapper;
    @Override
    public List<GroupMsgQuery> getByGroupId(String id) {
        return groupMsgMapper.getByGroupId(id);
    }

    @Override
    public void removeByGroupId(String id) {
        LambdaQueryWrapper<GroupMsg> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GroupMsg::getGroup_id,Long.valueOf(id));
        super.remove(wrapper);
    }
}
