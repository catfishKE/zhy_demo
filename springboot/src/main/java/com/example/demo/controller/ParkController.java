package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Park;
import com.example.demo.mapper.ParkMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/park")
public class ParkController {
    @Resource
    ParkMapper parkMapper;
    //新增



    @PostMapping
    public Result <?> save(@RequestBody Park park) {
        parkMapper.insert(park);
        return Result.success();
    }
    //查询
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String search) {

        LambdaQueryWrapper<Park> wrapper = Wrappers.<Park>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(Park::getName, search);
        }
        Page<Park> ParkPage = parkMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(ParkPage);
    }
    //更新
    @PutMapping
    public Result<?> update(@RequestBody Park Park) {
        parkMapper.updateById(Park);
        return Result.success();
    }
    //删除
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        parkMapper.deleteById(id);
        return Result.success();
    }
}
