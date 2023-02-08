package com.hiqgroup.hiq.entity;

import java.io.Serializable;

/**
 * 系统设置表(SmtParamsset)实体类
 *
 * @author liugaqiong
 * @since 2022-11-30 20:42:05
 */
public class SmtParamsset implements Serializable {
    private static final long serialVersionUID = 491688963271543888L;
    /**
     * 编号
     */
    private String id;
    /**
     * 是否记载日志
     */
    private Boolean issyslog;
    /**
     * 报告到期提醒天数
     */
    private Integer reporthintdays;
    /**
     * 黑白色调
     */
    private Boolean isblackwhite;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIssyslog() {
        return issyslog;
    }

    public void setIssyslog(Boolean issyslog) {
        this.issyslog = issyslog;
    }

    public Integer getReporthintdays() {
        return reporthintdays;
    }

    public void setReporthintdays(Integer reporthintdays) {
        this.reporthintdays = reporthintdays;
    }

    public Boolean getIsblackwhite() {
        return isblackwhite;
    }

    public void setIsblackwhite(Boolean isblackwhite) {
        this.isblackwhite = isblackwhite;
    }

}
