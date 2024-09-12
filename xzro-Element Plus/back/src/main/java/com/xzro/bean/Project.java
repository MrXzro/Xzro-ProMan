package com.xzro.bean;

/**
 * ClassName: Project
 * Package: com.xzro.bean
 * Description:
 *
 * @Author Xzro
 * @Create 2024/8/31 22:03
 * @Version 1.0
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project implements Serializable {
    //项目ID
    private Integer pid;
    //项目名称
    private String pname;
    //项目开始时间
    @JsonFormat(pattern = "yyyy年MM月dd日", timezone="GMT+8")
    private Date pstart;
    //项目结束时间
    @JsonFormat(pattern = "yyyy年MM月dd日", timezone="GMT+8")
    private Date pend;
    //项目进度
    private Integer pprogress;
    //项目描述
    private String pdescription;
    //项目成员
    private List<Employee> emps;
}
