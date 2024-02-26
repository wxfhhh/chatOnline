package com.chatOnline.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeQuery {
    private Long id;
    @TableField(value = "`from`")
    private Long from;
    @TableField(value = "`to`")
    private Long to;
    private String msg;
    private Integer type;
    private String time;
    private String toName;
    private String toEmail;
}
