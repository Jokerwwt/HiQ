package com.hiqgroup.hiq.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户资料表(SmtUser)实体类
 *
 * @author liugaqiong
 * @since 2022-01-04 16:13:18
 */
public class SmtUser implements Serializable {
    private static final long serialVersionUID = -92605142148756459L;
    /**
     * 编号
     */
    private String id;
    /**
     * 用户名
     */
    private String userid;
    /**
     * 用户姓名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 1.委托检测机构 2.运营机构 3.检测机构 4.监督机构
     */
    private String companyid;
    /**
     * 部门
     */
    private String deptid;
    /**
     * 头像
     */
    private Byte[] photo;
    /**
     * 电话
     */
    private String mobile;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 备注
     */
    private String remark;
    /**
     * 1.注册 2.启用 3.禁用
     */
    private String status;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 是否系统用户
     */
    private Boolean issysuser;
    /**
     * 操作员
     */
    private String edtbyuserid;
    /**
     * 操作时间
     */
    private Date edttime;


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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public Byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(Byte[] photo) {
        this.photo = photo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Boolean getIssysuser() {
        return issysuser;
    }

    public void setIssysuser(Boolean issysuser) {
        this.issysuser = issysuser;
    }

    public String getEdtbyuserid() {
        return edtbyuserid;
    }

    public void setEdtbyuserid(String edtbyuserid) {
        this.edtbyuserid = edtbyuserid;
    }

    public Date getEdttime() {
        return edttime;
    }

    public void setEdttime(Date edttime) {
        this.edttime = edttime;
    }

}
