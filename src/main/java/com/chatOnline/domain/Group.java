package com.chatOnline.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "`group`")
public class Group {
    private Long id;
    private String name;
    private Long owner_id;//群主
    private String create_time;
    private String url;//群头像
}
