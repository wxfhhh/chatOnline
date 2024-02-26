package com.chatOnline.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chatOnline.domain.User;
import com.chatOnline.domain.dto.UserSearch;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
     List<UserSearch> getFriendList(String id);
     List<UserSearch> getByKeyword(String keyword);
     UserSearch getOnline(String id);
}
