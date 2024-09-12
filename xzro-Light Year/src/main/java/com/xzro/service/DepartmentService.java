package com.xzro.service;

import com.xzro.Exception.XzroException;
import com.xzro.bean.Department;

import java.sql.SQLException;
import java.util.List;

/**
 * ClassName: DepartmentService
 * Package: com.xzro.service
 * Description:
 *
 * @Author Xzro
 * @Create 2024/8/18 14:37
 * @Version 1.0
 */
public interface DepartmentService {
    //添加
    boolean insert(Department d);
    //删除
    boolean delete(Integer did) throws  XzroException;
    boolean deleteAll(Integer did) throws XzroException;
    //修改
    boolean update(Department d) throws XzroException;
    //根据id查询
    Department selectByDid(Integer did);
    //查询所有
    List<Department> selectAll();
}
