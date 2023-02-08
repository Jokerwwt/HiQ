package com.hiqgroup.hiq.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 委托方订单(HqtCustomerorder)实体类
 *
 * @author liugaqiong
 * @since 2022-12-01 09:18:56
 */
public class HqtCustomerorder implements Serializable {
    private static final long serialVersionUID = 289658388719560106L;
    /**
     * 编号
     */
    private String id;
    /**
     * 订单编号
     */
    private String orderid;
    /**
     * 订单名称
     */
    private String ordername;
    /**
     * 订单日期
     */
    private Date orderdate;
    /**
     * 期望完成日期
     */
    private Date hopeenddate;
    /**
     * 实际完成日期
     */
    private Date finisheddate;
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
     * 金额
     */
    private Double amt;
    /**
     * 已收金额
     */
    private Double inamt;
    /**
     * 1.未收款 2.部分收款 3.已收款
     */
    private String instatus;
    /**
     * 1.未开票 2.已开未寄  3.已开寄出 4.已收发票
     */
    private String invoicestatus;
    /**
     * -1.作废 0.草稿 1.提交 2.审核 3.检测中 4. 检测完成 5.已结案
     */
    private String status;
    /**
     * 发票编号
     */
    private String invoiceid;
    /**
     * 过程记录
     */
    private String procrecord;
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
     * 来源申请单号
     */
    private String applyorderid;
    /**
     * 负责人
     */
    private String chargeman;
    /**
     * 联系电话
     */
    private String telno;
    /**
     * 委托人
     */
    private String orderman;
    /**
     * 推荐检测机构
     */
    private String checkers;
    /**
     * 收款备注
     */
    private String inremark;
    /**
     * 发票备注
     */
    private String invoiceremark;
    /**
     * 收款日期
     */
    private Date indate;
    /**
     * 收款录入人
     */
    private String inrecorder;
    /**
     * 发票日期
     */
    private Date invoicedate;
    /**
     * 发票录入人
     */
    private String invoicerecorder;


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

    public Date getFinisheddate() {
        return finisheddate;
    }

    public void setFinisheddate(Date finisheddate) {
        this.finisheddate = finisheddate;
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

    public Double getInamt() {
        return inamt;
    }

    public void setInamt(Double inamt) {
        this.inamt = inamt;
    }

    public String getInstatus() {
        return instatus;
    }

    public void setInstatus(String instatus) {
        this.instatus = instatus;
    }

    public String getInvoicestatus() {
        return invoicestatus;
    }

    public void setInvoicestatus(String invoicestatus) {
        this.invoicestatus = invoicestatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvoiceid() {
        return invoiceid;
    }

    public void setInvoiceid(String invoiceid) {
        this.invoiceid = invoiceid;
    }

    public String getProcrecord() {
        return procrecord;
    }

    public void setProcrecord(String procrecord) {
        this.procrecord = procrecord;
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

    public String getApplyorderid() {
        return applyorderid;
    }

    public void setApplyorderid(String applyorderid) {
        this.applyorderid = applyorderid;
    }

    public String getChargeman() {
        return chargeman;
    }

    public void setChargeman(String chargeman) {
        this.chargeman = chargeman;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
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

    public String getInremark() {
        return inremark;
    }

    public void setInremark(String inremark) {
        this.inremark = inremark;
    }

    public String getInvoiceremark() {
        return invoiceremark;
    }

    public void setInvoiceremark(String invoiceremark) {
        this.invoiceremark = invoiceremark;
    }

    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }

    public String getInrecorder() {
        return inrecorder;
    }

    public void setInrecorder(String inrecorder) {
        this.inrecorder = inrecorder;
    }

    public Date getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(Date invoicedate) {
        this.invoicedate = invoicedate;
    }

    public String getInvoicerecorder() {
        return invoicerecorder;
    }

    public void setInvoicerecorder(String invoicerecorder) {
        this.invoicerecorder = invoicerecorder;
    }

}
