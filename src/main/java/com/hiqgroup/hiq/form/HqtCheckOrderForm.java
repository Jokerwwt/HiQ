package com.hiqgroup.hiq.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hiqgroup.hiq.entity.HqtCheckorder;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel
public class HqtCheckOrderForm extends HqtCheckorder {
    /**
     * 订单日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date orderdate;
    /**
     * 报告到期日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date reportenddate;
    /**
     * 期望完成日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date hopeenddate;
    /**
     * 实际完成日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date finisheddate;
    /**
     * 付款日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date paydate;
    /**
     * 发票日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date invoicedate;
    /**
     * 操作员姓名
     */
    private String edtbyusername;

    /**
     * 客户名称
     */
    private String checkername;
    /**
     * 对应委托订单编号
     */
    private String custorder;
    /**
     * 样品名称
     */
    private String samplename;
    /**
     * 发单人
     */
    private String sendername;
    /**
     * 接单人
     */
    private String acceptername;
    /**
     * 付款录入人姓名
     */
    private String payrecordername;
    /**
     * 发票录入人姓名
     */
    private String invoicerecordername;


    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public Date getReportenddate() {
        return reportenddate;
    }

    public void setReportenddate(Date reportenddate) {
        this.reportenddate = reportenddate;
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

    public String getEdtbyusername() {
        return edtbyusername;
    }

    public void setEdtbyusername(String edtbyusername) {
        this.edtbyusername = edtbyusername;
    }

    public String getCheckername() {
        return checkername;
    }

    public void setCheckername(String checkername) {
        this.checkername = checkername;
    }

    public String getCustorder() {
        return custorder;
    }

    public void setCustorder(String custorder) {
        this.custorder = custorder;
    }

    public String getSamplename() {
        return samplename;
    }

    public void setSamplename(String samplename) {
        this.samplename = samplename;
    }

    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    public String getAcceptername() {
        return acceptername;
    }

    public void setAcceptername(String acceptername) {
        this.acceptername = acceptername;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public Date getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(Date invoicedate) {
        this.invoicedate = invoicedate;
    }

    public String getPayrecordername() {
        return payrecordername;
    }

    public void setPayrecordername(String payrecordername) {
        this.payrecordername = payrecordername;
    }

    public String getInvoicerecordername() {
        return invoicerecordername;
    }

    public void setInvoicerecordername(String invoicerecordername) {
        this.invoicerecordername = invoicerecordername;
    }
}
