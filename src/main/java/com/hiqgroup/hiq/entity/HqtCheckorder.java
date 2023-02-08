package com.hiqgroup.hiq.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 检测方订单(HqtCheckorder)实体类
 *
 * @author liugaqiong
 * @since 2022-12-01 09:19:09
 */
public class HqtCheckorder implements Serializable {
    private static final long serialVersionUID = -51003884637817993L;
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
     * 检测机构编号
     */
    private String checkerid;
    /**
     * 对应委托订单编号
     */
    private String custorderid;
    /**
     * 联系人
     */
    private String contactor;
    /**
     * 报告预计时间
     */
    private Date reportexptime;
    /**
     * 金额
     */
    private Double amt;
    /**
     * 已支金额
     */
    private Double payamt;
    /**
     * 1.未付款 2.部分付款 3.已付款
     */
    private String paystatus;
    /**
     * 1.未收票 2.已收票
     */
    private String invoicestatus;
    /**
     * -1.作废 0.草稿 1.提交 2.已审核 3.已完工4.已结案
     */
    private String status;
    /**
     * 发票编号
     */
    private String invoiceid;
    /**
     * 报告编号
     */
    private String reportid;
    /**
     * 报告到期日期
     */
    private Date reportenddate;
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
     * 发单人
     */
    private String sender;
    /**
     * 联系电话
     */
    private String telno;
    /**
     * 1.合格 2.不合格
     */
    private String checkresult;
    /**
     * 接单人
     */
    private String accepter;
    /**
     * 付款备注
     */
    private String payremark;
    /**
     * 发票备注
     */
    private String invoiceremark;
    /**
     * 付款日期
     */
    private Date paydate;
    /**
     * 付款录入人
     */
    private String payrecorder;
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

    public String getCheckerid() {
        return checkerid;
    }

    public void setCheckerid(String checkerid) {
        this.checkerid = checkerid;
    }

    public String getCustorderid() {
        return custorderid;
    }

    public void setCustorderid(String custorderid) {
        this.custorderid = custorderid;
    }

    public String getContactor() {
        return contactor;
    }

    public void setContactor(String contactor) {
        this.contactor = contactor;
    }

    public Date getReportexptime() {
        return reportexptime;
    }

    public void setReportexptime(Date reportexptime) {
        this.reportexptime = reportexptime;
    }

    public Double getAmt() {
        return amt;
    }

    public void setAmt(Double amt) {
        this.amt = amt;
    }

    public Double getPayamt() {
        return payamt;
    }

    public void setPayamt(Double payamt) {
        this.payamt = payamt;
    }

    public String getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(String paystatus) {
        this.paystatus = paystatus;
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

    public String getReportid() {
        return reportid;
    }

    public void setReportid(String reportid) {
        this.reportid = reportid;
    }

    public Date getReportenddate() {
        return reportenddate;
    }

    public void setReportenddate(Date reportenddate) {
        this.reportenddate = reportenddate;
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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public String getCheckresult() {
        return checkresult;
    }

    public void setCheckresult(String checkresult) {
        this.checkresult = checkresult;
    }

    public String getAccepter() {
        return accepter;
    }

    public void setAccepter(String accepter) {
        this.accepter = accepter;
    }

    public String getPayremark() {
        return payremark;
    }

    public void setPayremark(String payremark) {
        this.payremark = payremark;
    }

    public String getInvoiceremark() {
        return invoiceremark;
    }

    public void setInvoiceremark(String invoiceremark) {
        this.invoiceremark = invoiceremark;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public String getPayrecorder() {
        return payrecorder;
    }

    public void setPayrecorder(String payrecorder) {
        this.payrecorder = payrecorder;
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
