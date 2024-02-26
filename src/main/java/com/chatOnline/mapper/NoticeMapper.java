package com.chatOnline.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chatOnline.domain.Notice;
import com.chatOnline.domain.dto.NoticeQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
    public List<NoticeQuery> getByFromOrTo(String id);
}
