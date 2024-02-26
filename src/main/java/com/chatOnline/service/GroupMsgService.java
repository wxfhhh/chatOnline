package com.chatOnline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chatOnline.domain.GroupMsg;
import com.chatOnline.domain.dto.GroupMsgQuery;

import java.util.List;

public interface GroupMsgService extends IService<GroupMsg> {

    List<GroupMsgQuery> getByGroupId(String id);

    void removeByGroupId(String id);
}
