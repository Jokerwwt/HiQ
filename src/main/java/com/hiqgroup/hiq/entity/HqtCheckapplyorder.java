package com.hiqgroup.hiq.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 委托检测申请单(HqtCheckapplyorder)实体类
 *
 * @author liugaqiong
 * @since 2022-07-21 12:17:57
 */
public class HqtCheckapplyorder implements Serializable {
    private static final long serialVersionUID = -34058570309022649L;
    /**
     * 编号
     */
    private String id;
    /**
     * 申请单编号
     */
    private String orderid;
    /**
     * 申请单名称
     */
    private String ordername;
    /**
     * 申请单日期
     */
    private Date orderdate;
    /**
     * 期望完成日期
     */
    private Date hopeenddate;
    /**
     * 客户编号
     */
    private String customerid;
    /**
     * 样品名称
     */
    private String samplename;
    /**
     * 联系人
     */
    private String contactor;
    /**
     * 测试需求简述
     */
    private String checkrequire;
    /**
     * 报价金额
     */
    private Double amt;
    /**
     * -1.作废 0.草稿 1.提交 2.已接单 3.已报价 4.已确认 5.已下单
     */
    private String status;
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
    /**
     * 联系电话
     */
    private String telno;
    /**
     * 接单业务员
     */
    private String accepter;
    /**
     * 申请人
     */
    private String orderman;
    /**
     * 推荐检测机构
     */
    private String checkers;
    /**
     * 委托机构
     */
    private String tocompany;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public Date getHopeenddate() {
        return hopeenddate;
    }

    public void setHopeenddate(Date hopeenddate) {
        this.hopeenddate = hopeenddate;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getSamplename() {
        return samplename;
    }

    public void setSamplename(String samplename) {
        this.samplename = samplename;
    }

    public String getContactor() {
        return contactor;
    }

    public void setContactor(String contactor) {
        this.contactor = contactor;
    }

    public String getCheckrequire() {
        return checkrequire;
    }

    public void setCheckrequire(String checkrequire) {
        this.checkrequire = checkrequire;
    }

    public Double getAmt() {
        return amt;
    }

    public void setAmt(Double amt) {
        this.amt = amt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public String getAccepter() {
        return accepter;
    }

    public void setAccepter(String accepter) {
        this.accepter = accepter;
    }

    public String getOrderman() {
        return orderman;
    }

    public void setOrderman(String orderman) {
        this.orderman = orderman;
    }

    public String getCheckers() {
        return checkers;
    }

    public void setCheckers(String checkers) {
        this.checkers = checkers;
    }

    public String getTocompany() {
        return tocompany;
    }

    public void setTocompany(String tocompany) {
        this.tocompany = tocompany;
    }

}
