package com.hiqgroup.hiq.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统角色表(SmtRole)实体类
 *
 * @author liugaqiong
 * @since 2021-11-02 14:28:34
 */
public class SmtRole implements Serializable {
    private static final long serialVersionUID = 492312059258368504L;
    /**
     * 角色编号
     */
    private String id;
    /**
     * 角色名称
     */
    private String rolename;
    /**
     * 1.系统 2.用户
     */
    private Integer roletype;
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

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Integer getRoletype() {
        return roletype;
    }

    public void setRoletype(Integer roletype) {
        this.roletype = roletype;
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
