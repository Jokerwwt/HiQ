package com.hiqgroup.hiq.entity;

import java.io.Serializable;

/**
 * 用户权限表(SmtUserright)实体类
 *
 * @author liugaqiong
 * @since 2021-11-02 14:28:47
 */
public class SmtUserright implements Serializable {
    private static final long serialVersionUID = 724732695171632172L;
    /**
     * 用户编号
     */
    private String userid;
    /**
     * 菜单编号
     */
    private String menuid;
    /**
     * 1.系统角色，2.自定义角色
     */
    private String rightvalue;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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
