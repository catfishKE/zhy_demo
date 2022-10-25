package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("takeAwayMembers")
@Data
public class TakeAwayMembers {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String idNumber;
    private String name;
    private String platform;
    private String photo;
    private String phoneNumber;
    private Integer nucleicAcidRecord;
    private String criminalRecord;
    private String historyOfInfection;
    private String creditRecord;
    private String healthCertificate;
    private String medicalReport;
}
