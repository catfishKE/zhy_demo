package com.example.demo.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Admin;
import com.example.demo.mapper.AdminMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    AdminMapper adminMapper;


    @PostMapping("/login")
    public Result<?> login(@RequestBody Admin admin){
        Admin res = adminMapper.selectOne(Wrappers.<Admin>lambdaQuery().eq(Admin::getUsername, admin.getUsername()).eq(Admin::getPassword,admin.getPassword()));
        if(res == null){
            return  Result.error("-1","用户名或密码错误");
        }
        return Result.success();
    }

    @PostMapping
    public Result<?> save(@RequestBody Admin admin){
        if(admin.getPassword() == null){
            admin.setPassword("123456");
        }
        adminMapper.insert(admin);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Admin admin){
        adminMapper.updateById(admin);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        adminMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum ,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Admin> wrapper = Wrappers.<Admin>lambdaQuery();
        Page<Admin> adminPage = adminMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(adminPage);
    }
}
