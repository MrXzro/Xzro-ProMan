package com.xzro.mapper;

import com.xzro.bean.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: ProjectMapper
 * Package: com.xzro.mapper
 * Description:
 *
 * @Author Xzro
 * @Create 2024/8/31 22:00
 * @Version 1.0
 */
public interface ProjectMapper {
    //查询所有的项目
    List<Project> selectAll();
    //获取某个项目所有员工的eid
    List<Long> selectEidByPid(Integer pid);

    //删除某个项目和所有员工的关联关系
    void deletePidAndEidByPid(Integer pid);

    //添加某个项目和所有员工的关联关系
    void insertPidAndEid(@Param("pid") Integer pid,@Param("eids") Long[] eids);
}






































