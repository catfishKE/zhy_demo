package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("user")
@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private Integer age;
    private String gender;
    private String address;
}

