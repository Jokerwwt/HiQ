package com.hiqgroup.hiq.entity;

import java.io.Serializable;

/**
 * A类，业务员自己独立获得的
 * B类，公司推送，但业务员重点推进的
 * C类，公司推送的成熟客户，业务员(SmtCustomertype)实体类
 *
 * @author liugaqiong
 * @since 2021-12-20 12:57:30
 */
public class SmtCustomertype implements Serializable {
    private static final long serialVersionUID = 775320639737768008L;
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
