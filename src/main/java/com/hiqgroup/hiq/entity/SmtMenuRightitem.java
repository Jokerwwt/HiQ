package com.hiqgroup.hiq.entity;

import java.io.Serializable;

/**
 * 系统菜单功能项目表(SmtMenuRightitem)实体类
 *
 * @author liugaqiong
 * @since 2021-11-02 14:28:23
 */
public class SmtMenuRightitem implements Serializable {
    private static final long serialVersionUID = 922464165439699573L;
    /**
     * 菜单编号
     */
    private String menuid;
    /**
     * 功能编号
     */
    private Integer itemid;
    /**
     * 功能名称
     */
    private String itemname;


    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

}
