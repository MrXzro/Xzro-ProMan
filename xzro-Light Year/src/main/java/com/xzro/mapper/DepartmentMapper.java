package com.xzro.mapper;

import com.xzro.bean.Department;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * ClassName: DepartmentDao
 * Package: com.xzro.dao
 * Description:
 *
 * @Author Xzro
 * @Create 2024/8/18 13:19
 * @Version 1.0
 */
public interface DepartmentMapper {
    //添加
    int insert(Department d);
    //根据ID删除
    int delete(Integer did);
    void deleteTransaction(Integer did);
    //删除部门下的所有员工
    void deleteAllTransaction(Integer did);
    //根据ID修改
    int update(Department d);
    //查询所有部门
    List<Department> selectAll();
    //根据部门名称查询
    Department selectByDname(String dname);

    //根据ID查询
    Department selectByDid(Integer did);
}
