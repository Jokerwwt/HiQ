package com.hiqgroup.hiq.entity;

import java.io.Serializable;

/**
 * 日常动态类别(HpgDailytype)实体类
 *
 * @author liugaqiong
 * @since 2021-12-20 14:03:10
 */
public class HpgDailytype implements Serializable {
    private static final long serialVersionUID = 890246981730559994L;
    /**
     * 类别编号
     */
    private String id;
    /**
     * 类别名称
     */
    private String typename;
    /**
     * 备注
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
