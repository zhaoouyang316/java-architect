package com.zoy.mybatisjpa.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : owen
 * @email zhaoouyang163@gmail.com
 * @date : 2019/4/18
 */
@Data
public class Blog implements Serializable{

    private static final long serialVersionUID = -4061875203869806207L;

    private Long id;
    private String name;
    private String title;
    private String author;
    private String content;
    private String source;
    private Integer visits;
    private Date createTime;
    private Date updateTime;
    private String remark;
    private Integer status;
    private Long createper;

}
