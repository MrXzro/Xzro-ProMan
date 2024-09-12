package com.xzro.mapper;

import com.xzro.bean.Employee;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.util.List;

/**
 * ClassName: EmployeeDao
 * Package: com.xzro.dao
 * Description:
 *
 * @Author Xzro
 * @Create 2024/8/18 21:00
 * @Version 1.0
 */
public interface EmployeeMapper {
    //分页查询
    List<Employee> selectByEName(String name, Integer beginIndex, Integer everyPage);
    List<Employee> selectAll();
    Employee selectByEid(Integer eid);

    // 添加
    int insert(Employee e) ;
    //删除
    int delete(Integer eid);
    //更新
    int update(Employee e) ;
}
