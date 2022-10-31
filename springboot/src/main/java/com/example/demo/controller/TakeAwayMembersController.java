package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.TakeAwayMembers;
import com.example.demo.mapper.TakeAwayMembersMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/takeAwayMembers")
public class TakeAwayMembersController {
    @Resource
    TakeAwayMembersMapper takeAwayMembersMapper;
    //新增

    @PostMapping("/onLogin")
    public String func(@RequestBody JSONObject req){
        return req.getStr("code");
    }

    @PostMapping("/register")
    public String register(@RequestBody JSONObject req){
        TakeAwayMembers newMember = new TakeAwayMembers();
        newMember.setName(req.getStr("name"));
        newMember.setIdNumber(req.getStr("IdNumber"));
        newMember.setPhoneNumber(req.getStr("phoneNumber"));
        takeAwayMembersMapper.insert(newMember);
        return "returned";
    }

    @PostMapping
    public Result <?> save(@RequestBody TakeAwayMembers takeAwayMembers) {

        takeAwayMembersMapper.insert(takeAwayMembers);
        return Result.success();
    }
    //查询
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String search) {

        LambdaQueryWrapper<TakeAwayMembers> wrapper = Wrappers.<TakeAwayMembers>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(TakeAwayMembers::getName, search);
        }
        Page<TakeAwayMembers> takeAwayMembersPage = takeAwayMembersMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(takeAwayMembersPage);
    }
    //更新
    @PutMapping
    public Result<?> update(@RequestBody TakeAwayMembers takeAwayMembers) {
        takeAwayMembersMapper.updateById(takeAwayMembers);
        return Result.success();
    }
    //删除
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        takeAwayMembersMapper.deleteById(id);
        return Result.success();
    }
}
