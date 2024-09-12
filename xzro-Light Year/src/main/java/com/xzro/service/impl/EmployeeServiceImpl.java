package com.xzro.service.impl;

import com.github.pagehelper.PageHelper;
import com.xzro.bean.Employee;
import com.xzro.mapper.EmployeeMapper;
import com.xzro.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * ClassName: EmployeeServiceImpl
 * Package: com.xzro.service.impl
 * Description:
 *
 * @Author Xzro
 * @Create 2024/8/18 21:26
 * @Version 1.0
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> selectAll(){
        return employeeMapper.selectAll();
    }

    @Override
    public Employee selectByEid(Integer eid){
        return employeeMapper.selectByEid(eid);
    }

    @Override
    public long count() {
//        return employeeMapper.count();
        return 1;
    }

    @Override
    public boolean insert(Employee e) {
        if (employeeMapper.insert(e)==1) {
            return true;
        }
        return false;
    }
    @Override
    public boolean delete(Integer eid) {
        if (employeeMapper.delete(eid)==1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Employee e)  {
        if (employeeMapper.update(e)==1) {
            return true;
        }
        return false;
    }
}
