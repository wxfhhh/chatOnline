package com.chatOnline.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 群聊消息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupMsg {
    private Long id;
    private Long group_id;//群聊id
    private Long member_id;//发送消息人的id
    private String content;//发送的消息内容
    private String time;
}
