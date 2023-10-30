package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    EmpMapper empMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        Integer start = (page-1)*pageSize;
        PageBean pageBean = new PageBean ( empMapper.count (name,gender,begin,end),
                empMapper.page ( name,gender,begin,end,start,pageSize ) );
        return pageBean;
    }

    public void delete(List<Integer> ids){
        empMapper.delete(ids);
    }

    public void add(Emp emp){
        empMapper.add(emp);
    }
    public Emp getByid(Integer id){

        return empMapper.getByid(id);
    }

    public void update(Emp emp){
        empMapper.update(emp);

    }

    @Override
    public Emp login(Emp emp) {
        Emp ret = empMapper.getByusernameandpassword ( emp );
        return ret;
    }
}
