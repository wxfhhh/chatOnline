package com.chatOnline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chatOnline.domain.Group;

import java.util.List;

public interface GroupService extends IService<Group> {

    List<String> getMyGroup(String id);
    List<Group> getByKeyword(String keyword);
}
