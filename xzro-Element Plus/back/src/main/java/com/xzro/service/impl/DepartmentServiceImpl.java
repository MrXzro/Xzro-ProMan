package com.xzro.service.impl;

import com.xzro.Exception.XzroException;
import com.xzro.bean.Department;
import com.xzro.mapper.DepartmentMapper;
import com.xzro.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * ClassName: DepartmentServiceImpl
 * Package: com.xzro.service.impl
 * Description:
 *
 * @Author Xzro
 * @Create 2024/8/18 14:41
 * @Version 1.0
 */
@Service

public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public boolean insert(Department d){
        if (departmentMapper.insert(d)==1){
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer did) throws  XzroException {
        Department department = departmentMapper.selectByDid(did);
        if ( department == null) {
            throw new XzroException("删除的部门不存在！");
        }
        if (departmentMapper.delete(did) == 1) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deleteAll(Integer did) throws XzroException {
            departmentMapper.deleteTransaction(did);
            departmentMapper.deleteAllTransaction(did);
            return true;
    }

    @Override
    public boolean update(Department d) throws  XzroException {
        if (departmentMapper.selectByDname(d.getDname())!=null) {
            if (!d.getDid().equals(departmentMapper.selectByDname(d.getDname()).getDid())){
                throw new XzroException("修改的部门名称已存在，无法重复添加！");
            }
        }
        if(departmentMapper.update(d) == 1){
            return true;
        }
        return false;
    }

    @Override
    public Department selectByDid(Integer did)  {
        return departmentMapper.selectByDid(did);
    }

    @Override
    public List<Department> selectAll() {
        return departmentMapper.selectAll();
    }
}
