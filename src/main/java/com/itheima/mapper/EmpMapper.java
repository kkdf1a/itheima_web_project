package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    public Long count(String name, Short gender, LocalDate begin, LocalDate end);


    public List<Emp> page(String name, Short gender, LocalDate begin, LocalDate end,
                   Integer start, Integer pageSize);

    void delete(List<Integer> ids);

    @Insert ( "insert into emp(username,name,gender,image,job,entrydate,dept_id,create_time,update_time)" +
            "values(#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})" )
    void add(Emp emp);

    @Select ( "select * from emp where id = #{id}" )
    Emp getByid(Integer id);

    void update(Emp emp);

    @Select ( "select * from emp where username = #{username} and password = #{password}" )
    Emp getByusernameandpassword(Emp emp);

    @Delete ( "delete from emp where dept_id = #{deptId}" )
    void deleteByDeptId(Integer deptId);
}
