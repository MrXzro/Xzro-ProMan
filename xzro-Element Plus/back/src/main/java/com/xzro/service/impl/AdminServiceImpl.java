package com.xzro.service.impl;

import com.xzro.bean.Admin;
import com.xzro.mapper.AdminMapper;
import com.xzro.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * ClassName: AdminServiceImpl
 * Package: com.xzro.service.impl
 * Description:
 *
 * @Author Xzro
 * @Create 2024/8/17 9:03
 * @Version 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin login(String username){
        return adminMapper.selectByUserNameAndPassword(username);
    }

    @Override
    public List<Map<String, Object>> selectTop5DnameAndCount(){
        return adminMapper.selectTop5DnameAndCount();
    }
}
