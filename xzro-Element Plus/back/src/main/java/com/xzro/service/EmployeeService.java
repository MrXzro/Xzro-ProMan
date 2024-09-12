package com.xzro.service;

import com.xzro.bean.Employee;


import java.sql.SQLException;
import java.util.List;

/**
 * ClassName: EmployeeService
 * Package: com.xzro.service
 * Description:
 *
 * @Author Xzro
 * @Create 2024/8/18 21:25
 * @Version 1.0
 */
public interface EmployeeService {
    //分页查询
    List<Employee> selectAll();
    Employee selectByEid(Integer eid);

    //查询总共有多少条
    long count();
    boolean insert(Employee e);

    boolean delete(Integer eid);
    boolean update(Employee e);
}
