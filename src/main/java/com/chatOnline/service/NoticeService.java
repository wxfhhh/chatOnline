package com.chatOnline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chatOnline.domain.Notice;
import com.chatOnline.domain.dto.NoticeQuery;

import java.util.List;

public interface NoticeService extends IService<Notice> {
    List<NoticeQuery> getByFromOrTo(String id);
    Notice getByFromAndTo(Notice notice);
}
