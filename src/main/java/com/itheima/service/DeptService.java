package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> list();

    int delete(Integer id);
    void add(Dept dept);

    Dept getdeptById(Integer id);

    void update(Dept dept);
}
