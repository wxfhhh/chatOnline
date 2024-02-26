package com.chatOnline.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 群聊的成员
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private Long id;
    private Long group_id;
    private Long member_id;
    private String time;//加入时间
}
