package com.chatOnline.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Notice {
    private Long id;
    @TableField(value = "`from`")
    private Long from;
    @TableField(value = "`to`")
    private Long to;
    /**
     * 报错 :Parameter index out of range (1 > number of parameters, which is 0).
     * 原因: ` 写成了 '
     */
    private String msg;
    private Integer type;
    //0 发送好友申请 1同意好友申请
    private String time;
}
