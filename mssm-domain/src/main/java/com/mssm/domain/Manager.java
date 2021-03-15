package com.mssm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 管理员类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Manager {
    private Integer id;         //用户id
    private String name;        //用户昵称
    private String phone;       //注册手机

    private String portrait;    //用户头像地址
    private String password;    //用户密码（可以为空，支持只用验证码注册、登录）

    private Integer status;     //用户状态: 0-不能登录, 1-能登录
    private Integer locked;     //是否锁定: 0-锁定,     1-未锁定
    private Integer deleted;    //是否删除: 0-删除,     1-未删除

    private Date createtime;    //注册时间
    private Date updatetime;    //更新时间
    private Date deletetime;    //删除时间
}
