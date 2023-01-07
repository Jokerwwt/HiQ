package com.hiqgroup.hiq.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 日常动态(HpgDaily)实体类
 *
 * @author liugaqiong
 * @since 2022-02-05 13:32:05
 */
public class HpgDaily implements Serializable {
    private static final long serialVersionUID = 977056160999212367L;
    /**
     * 编号
     */
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 起草人
     */
    private String writer;
    /**
     * 发布人
     */
    private String publisher;
    /**
     * 发布日期
     */
    private Date publishdate;
    /**
     * 类别
     */
    private String dailytype;
    /**
     * 访问量
     */
    private Integer viewtotal;
    /**
     * 是否轮播
     */
    private Boolean isreshow;
    /**
     * 是否推荐
     */
    private Boolean isnotify;
    /**
     * 内容
     */
    private String content;
    /**
     * 操作员
     */
    private String edtbyuserid;
    /**
     * 操作时间
     */
    private Date edttime;
    /**
     * 1.草稿 2.发布
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

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public String getDailytype() {
        return dailytype;
    }

    public void setDailytype(String dailytype) {
        this.dailytype = dailytype;
    }

    public Integer getViewtotal() {
        return viewtotal;
    }

    public void setViewtotal(Integer viewtotal) {
        this.viewtotal = viewtotal;
    }

    public Boolean getIsreshow() {
        return isreshow;
    }

    public void setIsreshow(Boolean isreshow) {
        this.isreshow = isreshow;
    }

    public Boolean getIsnotify() {
        return isnotify;
    }

    public void setIsnotify(Boolean isnotify) {
        this.isnotify = isnotify;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
