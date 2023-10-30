package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result getempList(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             String name, Short gender, LocalDate begin, LocalDate end){
        log.info ( "分页查询，参数：{},{},{},{}",page,pageSize,name,gender );
        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success (pageBean);
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        empService.delete(ids);

        return Result.success ();
    }

    @PostMapping
    public Result add(@RequestBody Emp emp){
        log.info ( emp.toString () );
        emp.setCreateTime ( LocalDateTime.now () );
        emp.setUpdateTime ( LocalDateTime.now () );
        empService.add(emp);

        return Result.success ();
    }
    @GetMapping("/{id}")
    public Result getempByid(@PathVariable Integer id){
        return Result.success (empService.getByid(id));
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){

        emp.setUpdateTime ( LocalDateTime.now () );
        empService.update(emp);

        return Result.success ();
    }
}
