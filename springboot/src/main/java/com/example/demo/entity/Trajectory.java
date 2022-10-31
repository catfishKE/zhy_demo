package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("trajectory")
@Data
public class Trajectory {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer idTakeawaymember;
    private String trajectory;
    private String createTime;

}

