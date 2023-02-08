package com.hiqgroup.hiq.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统菜单资料表(SmtMenu)实体类
 *
 * @author liugaqiong
 * @since 2021-11-02 14:28:19
 */
public class SmtMenu implements Serializable {
    private static final long serialVersionUID = 670114913380409468L;
    /**
     * 菜单编号
     */
    private String id;
    /**
     * 上级菜单编号
     */
    private String parentid;
    /**
     * 菜单名称（系统）
     */
    private String menusname;
    /**
     * 菜单名称（用户）
     */
    private String menuuname;
    /**
     * 页面链接
     */
    private String href;
    /**
     * 图标名称
     */
    private String iconname;
    /**
     * 序号
     */
    private Integer orderid;
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

    public String getMenusname() {
        return menusname;
    }

    public void setMenusname(String menusname) {
        this.menusname = menusname;
    }

    public String getMenuuname() {
        return menuuname;
    }

    public void setMenuuname(String menuuname) {
        this.menuuname = menuuname;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIconname() {
        return iconname;
    }

    public void setIconname(String iconname) {
        this.iconname = iconname;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
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
