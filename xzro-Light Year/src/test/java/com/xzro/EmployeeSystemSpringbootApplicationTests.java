package com.xzro;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzro.bean.Department;
import com.xzro.bean.Employee;
import com.xzro.mapper.DepartmentMapper;
import com.xzro.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EmployeeSystemSpringbootApplicationTests {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Test
    void contextLoads() {
        PageHelper.startPage(2, 5);
        List<Employee> employees = employeeMapper.selectAll();

        PageInfo<Employee> departmentPageInfo = new PageInfo<>(employees);
        System.out.println(departmentPageInfo);
    }

}
