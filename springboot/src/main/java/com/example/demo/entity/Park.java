package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("park")
@Data
public class Park {
//    @TableId(type = IdType.AUTO)
    private String name;
    private String  address;
    private String state;
}
