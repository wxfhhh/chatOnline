package com.chatOnline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chatOnline.domain.Notice;
import com.chatOnline.domain.dto.NoticeQuery;
import com.chatOnline.mapper.NoticeMapper;
import com.chatOnline.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;
    @Override
    public List<NoticeQuery> getByFromOrTo(String id) {
        return noticeMapper.getByFromOrTo(id);
    }

    @Override
    public Notice getByFromAndTo(Notice notice) {
        LambdaQueryWrapper<Notice> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Notice::getFrom,notice.getFrom())
                .eq(Notice::getTo,notice.getTo());
//                .eq(Notice::getType,notice.getType());
        return super.getOne(wrapper);
    }
}
