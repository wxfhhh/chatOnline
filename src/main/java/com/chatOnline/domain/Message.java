package com.chatOnline.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Long id;
    @TableField(value = "`from`")
    private Long from;
    @TableField(value = "`to`")
    private Long to;
    private String content;
}
