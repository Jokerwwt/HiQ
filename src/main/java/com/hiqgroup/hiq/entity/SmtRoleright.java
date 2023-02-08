package com.hiqgroup.hiq.entity;

import java.io.Serializable;

/**
 * 角色权限表(SmtRoleright)实体类
 *
 * @author liugaqiong
 * @since 2021-11-02 14:28:37
 */
public class SmtRoleright implements Serializable {
    private static final long serialVersionUID = 430163020242621950L;
    /**
     * 角色编号
     */
    private String roleid;
    /**
     * 菜单编号
     */
    private String menuid;
    /**
     * 1.系统角色，2.自定义角色
     */
    private String rightvalue;


    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getRightvalue() {
        return rightvalue;
    }

    public void setRightvalue(String rightvalue) {
        this.rightvalue = rightvalue;
    }

}
