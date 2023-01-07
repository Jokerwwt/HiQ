package com.hiqgroup.hiq.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志表(SmtSyslog)实体类
 *
 * @author liugaqiong
 * @since 2022-01-24 15:15:27
 */
public class SmtSyslog implements Serializable {
    private static final long serialVersionUID = 320194353306571279L;
    /**
     * 日志编号
     */
    private String id;
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
    private Date operatetime;
    /**
     * IP地址
     */
    private String ipaddress;
    /**
     * 操作内容
     */
    private String operate;
    /**
     * 浏览器
     */
    private String browserversion;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Date getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(Date operatetime) {
        this.operatetime = operatetime;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getBrowserversion() {
        return browserversion;
    }

    public void setBrowserversion(String browserversion) {
        this.browserversion = browserversion;
    }

}
