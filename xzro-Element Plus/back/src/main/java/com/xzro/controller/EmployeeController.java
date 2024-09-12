package com.xzro.controller;/**
 * ClassName: EmployeeController
 * Package: com.xzro.controller
 * Description:
 *
 * @Author Xzro
 * @Create 2024/8/18 21:28
 * @Version 1.0
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzro.bean.Employee;
import com.xzro.bean.RespBean;
import com.xzro.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/admin/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/selectByPage/{currentPage}")
    protected RespBean selectByPage(@PathVariable("currentPage") Integer currentPage) throws ServletException, IOException {
//        if (employees != null) {
            PageHelper.startPage(currentPage, 5);
            List<Employee> employees = employeeService.selectAll();
            PageInfo<Employee> employeePageInfo = new PageInfo<>(employees);
        System.out.println(employeePageInfo.getList());
            if (employeePageInfo.getList()!=null){
                return RespBean.ok("", employeePageInfo);
            }
//        }
        return RespBean.error("查询失败");
    }

    @PostMapping("/insert")
    protected RespBean insert(@Validated @RequestBody Employee employee) throws ServletException, IOException {
        if (employeeService.insert(employee)) {
            return RespBean.ok("添加成功");
        } else {
            return RespBean.error("添加失败");
        }


    }

    @PostMapping("/update")
    protected RespBean update(@Validated @RequestBody Employee employee) throws ServletException, IOException {
        List<Employee> employees = employeeService.selectAll();
        for (Employee item : employees) {
            if (!item.getEid().equals(employee.getEid())) {
                if (employee.getEno().equals(item.getEno())){
                    return RespBean.error("工号重复");
                }
            }
        }
        if (employeeService.update(employee)) {
            return RespBean.ok("修改成功");
        } else {
            return RespBean.error("修改失败");
        }

    }

    @PostMapping("/delete")
    protected RespBean delete(@RequestBody Map<String,Object> map) throws ServletException, IOException {
        if (employeeService.delete((Integer) map.get("eid"))) {
            return RespBean.ok("删除成功");
        } else {
            return RespBean.error("删除失败");
        }
    }

    @GetMapping("/selectByEid/{eid}")
    protected RespBean selectByEid(@PathVariable("eid") Integer eid) throws ServletException, IOException {
        Employee employee = employeeService.selectByEid(eid);
        if (employee != null) {
            return RespBean.ok("查询成功", employee);
        }
        return RespBean.error("查询失败");
    }
}
