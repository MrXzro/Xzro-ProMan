package com.xzro.controller;

import com.xzro.bean.Employee;
import com.xzro.bean.Project;
import com.xzro.bean.RespBean;
import com.xzro.service.EmployeeService;
import com.xzro.service.ProjectService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: ProjectController
 * Package: com.xzro.controller
 * Description:
 *
 * @Author Xzro
 * @Create 2024/8/31 22:32
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/admin/project")
public class ProjectController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ProjectService projectService;

    //查询所所有项目信息
    @GetMapping
    public RespBean selectAll(){
        List<Project> projects = projectService.selectAll();
        return RespBean.ok("查询成功", projects);
    }

    //获取所有用户的信息
    @GetMapping("/allEmployee/{pid}")
    public RespBean allEmployee(@PathVariable("pid") Integer pid){
        List<Map<String, Object>> mapList = employeeService.selectAll()
                .stream()
                .filter(e -> e.getEstate() == 1)
                .map(e -> {
                    Map<String, Object> employeeMap = new HashMap<>();
                    employeeMap.put("key", e.getEid());
                    employeeMap.put("label", e.getDepartment().getDname() + "-" + e.getEname());
                    return employeeMap;
                }).collect(Collectors.toList());

        List<Long> eids = projectService.selectEidByPid(pid);
        HashMap<String, Object> map = new HashMap<>();
        map.put("allEmployee", mapList);
        map.put("selectEids", eids);
        return RespBean.ok("获取成功", map);
    }

    @PostMapping("/pidAndEid")
    public RespBean insertPidAndEid(@RequestBody Map<String,Object> map){
        Integer pid = (Integer) map.get("pid");
        List<Integer> eidsList = (List<Integer>)map.get("eids");
        Long[] eids = new Long[eidsList.size()];
        for (int i = 0;i<eidsList.size();i++) {
            int eid = eidsList.get(i);
            eids[i] = new Long(eid+"");
        }
        projectService.insertPidAndEid(pid,eids);
        return RespBean.ok("分配员工成功");
    }

}





















