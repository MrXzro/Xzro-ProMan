package com.xzro.service;

import com.xzro.bean.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * ClassName: AdminService
 * Package: com.xzro.service
 * Description:
 *
 * @Author Xzro
 * @Create 2024/8/17 9:02
 * @Version 1.0
 */
public interface AdminService {
    Admin login( String username);
    List<Map<String,Object>> selectTop5DnameAndCount();


}
