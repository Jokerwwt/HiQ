package com.hiqgroup.hiq.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 友情链接(HpgLinks)实体类
 *
 * @author liugaqiong
 * @since 2021-12-26 17:50:15
 */
public class HpgLinks implements Serializable {
    private static final long serialVersionUID = 570791446070097422L;
    /**
     * 编号
     */
    private String id;
    /**
     * 链接名称
     */
    private String title;
    /**
     * 地址
     */
    private String address;
    /**
     * 操作员
     */
    private String edtbyuserid;
    /**
     * 操作时间
     */
    private Date edttime;
    /**
     * 1.启用 2.停用
     */
    private String status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
