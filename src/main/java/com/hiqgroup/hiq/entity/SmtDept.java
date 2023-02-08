package com.hiqgroup.hiq.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 单位部门资料表(SmtDept)实体类
 *
 * @author liugaqiong
 * @since 2021-12-12 15:38:27
 */
public class SmtDept implements Serializable {
    private static final long serialVersionUID = -50115791940431003L;
    /**
     * 部门编号
     */
    private String id;
    /**
     * 根部门值为:###
     */
    private String parentid;
    /**
     * 部门名称
     */
    private String deptname;
    /**
     * 所属单位
     */
    private String companyid;
    /**
     * 负责人
     */
    private String chargeman;
    /**
     * 联系人
     */
    private String contactman;
    /**
     * 联系人电话
     */
    private String telno;
    /**
     * 传真
     */
    private String faxno;
    /**
     * 备注
     */
    private String remark;
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

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getChargeman() {
        return chargeman;
    }

    public void setChargeman(String chargeman) {
        this.chargeman = chargeman;
    }

    public String getContactman() {
        return contactman;
    }

    public void setContactman(String contactman) {
        this.contactman = contactman;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public String getFaxno() {
        return faxno;
    }

    public void setFaxno(String faxno) {
        this.faxno = faxno;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
