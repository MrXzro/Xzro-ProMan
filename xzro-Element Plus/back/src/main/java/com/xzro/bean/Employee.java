package com.xzro.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 员工表
 * @TableName employee
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    /**
     * 主键
     */
    private Long eid;

    /**
     * 工号
     */
    @Max(value = 99999, message = "不得大于5位")
    private String eno;

    /**
     * 姓名
     */
    @NotBlank(message = "不能为空")
    private String ename;

    /**
     * 年龄
     */
    @NotNull(message = "不能为空")
    private Integer eage;

    /**
     * 性别
     */
    @NotBlank(message = "不能为空")
    private String egender;

    /**
     * 工种
     */
    @NotBlank(message = "不能为空")
    private String ejob;

    /**
     * 入职时间
     */
    @NotNull(message = "不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT + 8")
    private Date eentrydate;

    /**
     * 基本薪资
     */
    @NotNull(message = "不能为空")
    private BigDecimal esalary;

    /**
     * 在职状态(1.在职 2.离职)
     */
    @NotNull(message = "不能为空")
    private Integer estate;

    /**
     * 部门编号
     */
    private Integer did;
    //头像文件名称
    private String eavatar;
    //所在部门的信息
    private Department department;
}