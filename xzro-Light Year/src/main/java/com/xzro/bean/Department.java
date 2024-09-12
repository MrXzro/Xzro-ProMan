package com.xzro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 部门表
 * @TableName department
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Department {
    /**
     * 主键
     */
    private Integer did;

    /**
     * 部门名称
     */
    private String dname;

    /**
     * 部门位置
     */
    private String dlocation;
}