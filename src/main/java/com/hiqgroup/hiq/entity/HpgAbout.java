package com.hiqgroup.hiq.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 关于我们(HpgAbout)实体类
 *
 * @author liugaqiong
 * @since 2021-12-23 22:59:55
 */
public class HpgAbout implements Serializable {
    private static final long serialVersionUID = 614401003269109203L;
    /**
     * 编号
     */
    private String id;
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
     * 访问量
     */
    private Integer viewtotal;
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

    public Integer getViewtotal() {
        return viewtotal;
    }

    public void setViewtotal(Integer viewtotal) {
        this.viewtotal = viewtotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
