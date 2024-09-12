package com.xzro.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 员工表
 * @TableName employee
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Employee {
    /**
     * 主键
     */
    private Long eid;

    /**
     * 工号
     */
    private String eno;

    /**
     * 姓名
     */
    private String ename;

    /**
     * 年龄
     */
    private Integer eage;

    /**
     * 性别
     */
    private String egender;

    /**
     * 工种
     */
    private String ejob;

    /**
     * 入职时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT + 8")
    private Date eentrydate;

    /**
     * 基本薪资
     */
    private BigDecimal esalary;

    /**
     * 在职状态(1.在职 2.离职)
     */
    private Integer estate;

    /**
     * 部门编号
     */
    private Integer did;
    //所在部门的信息
    private Department department;
}