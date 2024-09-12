package com.xzro.mapper;

import com.xzro.bean.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * ClassName: AdminDao
 * Package: com.xzro.dao
 * Description:
 *
 * @Author Xzro
 * @Create 2024/8/17 8:51
 * @Version 1.0
 */
public interface AdminMapper {
    //根据用户名和密码的查询
    Admin selectByUserNameAndPassword(String username);

    //查询部门名称和部门人数
    List<Map<String,Object>> selectTop5DnameAndCount();

}
