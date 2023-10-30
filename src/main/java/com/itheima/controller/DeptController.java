package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result dept(){
        log.info ( "查询全部部门数据" );

        List<Dept> deptList = deptService.list();

        return Result.success (deptList);
    }

    @DeleteMapping("/{id}")
    public Result deletedept(@PathVariable Integer id){
        log.info ( "删除部门" );
        int ret = deptService.delete(id);

        return ret>0?Result.success ():Result.error ( "删除失败");
    }

    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info ( "增加部门" );

        deptService.add(dept);
        return Result.success ();
    }

    @GetMapping("/{id}")
    public Result getdeptByid(@PathVariable Integer id){

        return Result.success (deptService.getdeptById ( id ));
    }

    @PutMapping
    public Result update(@RequestBody  Dept olddept){
        log.info ( "修改部门" );
        Dept dept = deptService.getdeptById( olddept.getId ());
        dept.setUpdateTime ( LocalDateTime.now () );
        dept.setName ( olddept.getName () );
        deptService.update(dept);

        return Result.success ();
    }

}
