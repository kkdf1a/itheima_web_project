package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.DeptLog;
import com.itheima.service.DeptLogService;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;
    @Override
    public List<Dept> list(){


        return deptMapper.list ();
    }

    @Transactional
    public int delete(Integer id){

        try {
            empMapper.deleteByDeptId ( id );
            int i = 1/0;
            deptMapper.deleteById (id);
        }finally {
            DeptLog deptLog = new DeptLog (  );
            deptLog.setCreateTime ( LocalDateTime.now () );
            deptLog.setDescription ( "解散了"+id+"号部门" );
            deptLogService.insert(deptLog);

            return 0;
        }


    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime ( LocalDateTime.now () );
        dept.setUpdateTime ( LocalDateTime.now () );

        deptMapper.add(dept);
    }

    public Dept getdeptById(Integer id){
        return deptMapper.getById ( id );
    }

    public void update(Dept dept){
        deptMapper.update(dept);
    }
}
