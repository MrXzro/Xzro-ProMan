package com.xzro.controller;/**
 * ClassName: DepartmetController
 * Package: com.xzro.controller
 * Description:
 *
 * @Author Xzro
 * @Create 2024/8/18 15:08
 * @Version 1.0
 */

import cn.hutool.core.util.StrUtil;
import com.xzro.Exception.XzroException;
import com.xzro.bean.Department;
import com.xzro.bean.RespBean;
import com.xzro.service.DepartmentService;
import com.xzro.service.impl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/admin/department")
public class DepartmetController{
    @Autowired
    private DepartmentService departmentService;

    //添加
    @PostMapping("/insert")
    protected RespBean insert(String dname, String dlocation) throws ServletException, IOException {
        Department d = new Department(null, dname, dlocation);
        departmentService.insert(d);
        return RespBean.ok("添加成功");
    }
    //删除
    @PostMapping("/delete")
    protected RespBean delete(Integer did, Boolean isAll) throws ServletException, IOException {

        if (isAll){
            //事务删除全部
            try {
                if (departmentService.deleteAll(did)) {
                    return RespBean.ok("删除成功");
                }else {
                    return RespBean.error("删除失败");
                }
            }  catch (XzroException e) {
                e.printStackTrace();
                return RespBean.error(e.getMessage());

            }
        }else {
            //普通删除
            try {
                if (departmentService.delete(did)) {
                    return RespBean.ok("删除成功");

                }
                return RespBean.error("删除失败");

            }  catch (XzroException e) {
                e.printStackTrace();

                return RespBean.error(e.getMessage());

            }
        }

    }

    //修改
    @PostMapping("/update")
    protected RespBean update(String dname, String dlocation, Integer did) throws ServletException, IOException {
        //修改
        Department department = new Department(did, dname, dlocation);
        System.out.println(department);
        try {
            if (departmentService.update(department)){
                return RespBean.ok("修改成功");

            }else {
                return RespBean.error("修改失败");
            }
        }  catch (XzroException e) {
            e.printStackTrace();
            return RespBean.error(e.getMessage());
        }
    }

    //根据id查询
    @GetMapping("/selectByDid")
    protected RespBean selectByDid(Integer did) throws ServletException, IOException {
        //查询
        Department department = null;

        department = departmentService.selectByDid(did);
        if(department!=null){
            return RespBean.ok("查询成功", department);
        }else{
            return RespBean.error("查询失败");
        }

    }
    @GetMapping("/selectAll")
    //查询所有
    protected RespBean selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询
            List<Department> departments = null;
            departments = departmentService.selectAll();
            if (departments!=null){
                return RespBean.ok("查询成功", departments);
            }
            return RespBean.error("查询失败");
    }

}
