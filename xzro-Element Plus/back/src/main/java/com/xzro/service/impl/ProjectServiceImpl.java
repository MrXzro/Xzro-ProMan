package com.xzro.service.impl;

import com.xzro.Exception.XzroException;
import com.xzro.bean.Project;
import com.xzro.mapper.ProjectMapper;
import com.xzro.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: ProjectServiceImpl
 * Package: com.xzro.service.impl
 * Description:
 *
 * @Author Xzro
 * @Create 2024/8/31 22:29
 * @Version 1.0
 */
@Service

public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Override
    public List<Project> selectAll() {
        return projectMapper.selectAll();
    }

    @Override
    public List<Long> selectEidByPid(Integer pid) {
        return projectMapper.selectEidByPid(pid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertPidAndEid(Integer pid, Long[] eids) {


        //删除特定的项目和员工的关系
        projectMapper.deletePidAndEidByPid(pid);
        //添加特定的项目和员工的关系
        if (eids.length != 0){
            projectMapper.insertPidAndEid(pid,eids);

        }
    }
}
