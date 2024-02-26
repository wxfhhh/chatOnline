package com.chatOnline.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询时的群组消息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMsgQuery {
    private Long id;
    private Long group_id;
    private Long member_id;
    private String content;
    private String time;
    private String member_name;
    private String member_url;
}
