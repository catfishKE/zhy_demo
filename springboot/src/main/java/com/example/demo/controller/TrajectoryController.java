package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Trajectory;
import com.example.demo.mapper.TrajectoryMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/trajectory")
public class TrajectoryController {
    @Resource
    TrajectoryMapper trajectoryMapper;
    //新增



    @PostMapping
    public Result <?> save(@RequestBody Trajectory trajectory) {

        trajectoryMapper.insert(trajectory);
        return Result.success();
    }
    //查询
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String search) {

        LambdaQueryWrapper<Trajectory> wrapper = Wrappers.<Trajectory>lambdaQuery();

        Page<Trajectory> trajectoryPage = trajectoryMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(trajectoryPage);
    }
    //更新
    @PutMapping
    public Result<?> update(@RequestBody Trajectory trajectory) {
        trajectoryMapper.updateById(trajectory);
        return Result.success();
    }
    //删除
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        trajectoryMapper.deleteById(id);
        return Result.success();
    }
}
