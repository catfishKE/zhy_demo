package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Blob;
import java.util.Date;

@TableName("track")
@Data
public class Track {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Blob track;
    private Date time;
}
