package com.hiqgroup.hiq.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hiqgroup.hiq.entity.HqtCustomerorder;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel
public class HqtOrderViewForm extends HqtCustomerorder {
    /**
     * 订单日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date orderdate;
    /**
     * 期望完成日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date hopeenddate;
    /**
     * 客户名称
     */
    private String customername;
    /**
     * 委托机构类别
     */
    private String customertypename;
    /**
     * 申请人姓名
     */
    private String ordermanname;
    /**
     * 负责人姓名
     */
    private String chargemanname;
    //--------检测单属性--------------------------------
    /**
     * 检测单编号
     */
    private String checkid;
    /**
     * 检测单单据编号
     */
    private String checkorderid;
    /**
     * 检测单名称
     */
    private String checkordername;
    /**
     * 检测单日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date checkorderdate;
    /**
     * 检测单期望完成日期
     */
    private Date checkhopeenddate;
    /**
     * 检测单实际完成日期
     */
    private Date checkfinisheddate;
    /**
     * 检测单检测机构编号
     */
    private String checkerid;
    /**
     * 检测单联系人
     */
    private String checkcontactor;
    /**
     * 检测单报告预计时间
     */
    private Date reportexptime;
    /**
     * 检测单金额
     */
    private Double checkamt;
    /**
     * 检测单已支金额
     */
    private Double checkpayamt;
    /**
     * 1.未付款 2.部分付款 3.已付款
     */
    private String checkpaystatus;
    /**
     * 1.未收票 2.已收票
     */
    private String checkinvoicestatus;
    /**
     * -1.作废 0.草稿 1.提交 2.已审核 3.已完工4.已结案
     */
    private String checkstatus;
    /**
     * 检测单发票编号
     */
    private String checkinvoiceid;
    /**
     * 报告编号
     */
    private String reportid;
    /**
     * 报告到期日期
     */
    private Date reportenddate;
    /**
     * 检测单过程记录
     */
    private String checkprocrecord;
    /**
     * 检测单备注
     */
    private String checkremark;
    /**
     * 检测单发单人
     */
    private String sender;
    /**
     * 检测单联系电话
     */
    private String checktelno;
    /**
     * 收款日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date indate;
    /**
     * 检测机构名称
     */
    private String checkername;
    /**
     * 检测单发单人姓名
     */
    private String sendername;
    /**
     * 检测结果
     */
    private String checkresult;
    //------------------------------------------------
    private Double grossprofit;

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

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getOrdermanname() {
        return ordermanname;
    }

    public void setOrdermanname(String ordermanname) {
        this.ordermanname = ordermanname;
    }

    public String getChargemanname() {
        return chargemanname;
    }

    public void setChargemanname(String chargemanname) {
        this.chargemanname = chargemanname;
    }

    public String getCheckid() {
        return checkid;
    }

    public void setCheckid(String checkid) {
        this.checkid = checkid;
    }

    public String getCheckorderid() {
        return checkorderid;
    }

    public void setCheckorderid(String checkorderid) {
        this.checkorderid = checkorderid;
    }

    public String getCheckordername() {
        return checkordername;
    }

    public void setCheckordername(String checkordername) {
        this.checkordername = checkordername;
    }

    public Date getCheckorderdate() {
        return checkorderdate;
    }

    public void setCheckorderdate(Date checkorderdate) {
        this.checkorderdate = checkorderdate;
    }

    public Date getCheckhopeenddate() {
        return checkhopeenddate;
    }

    public void setCheckhopeenddate(Date checkhopeenddate) {
        this.checkhopeenddate = checkhopeenddate;
    }

    public Date getCheckfinisheddate() {
        return checkfinisheddate;
    }

    public void setCheckfinisheddate(Date checkfinisheddate) {
        this.checkfinisheddate = checkfinisheddate;
    }

    public String getCheckerid() {
        return checkerid;
    }

    public void setCheckerid(String checkerid) {
        this.checkerid = checkerid;
    }

    public String getCheckcontactor() {
        return checkcontactor;
    }

    public void setCheckcontactor(String checkcontactor) {
        this.checkcontactor = checkcontactor;
    }

    public Date getReportexptime() {
        return reportexptime;
    }

    public void setReportexptime(Date reportexptime) {
        this.reportexptime = reportexptime;
    }

    public Double getCheckamt() {
        return checkamt;
    }

    public void setCheckamt(Double checkamt) {
        this.checkamt = checkamt;
    }

    public Double getCheckpayamt() {
        return checkpayamt;
    }

    public void setCheckpayamt(Double checkpayamt) {
        this.checkpayamt = checkpayamt;
    }

    public String getCheckpaystatus() {
        return checkpaystatus;
    }

    public void setCheckpaystatus(String checkpaystatus) {
        this.checkpaystatus = checkpaystatus;
    }

    public String getCheckinvoicestatus() {
        return checkinvoicestatus;
    }

    public void setCheckinvoicestatus(String checkinvoicestatus) {
        this.checkinvoicestatus = checkinvoicestatus;
    }

    public String getCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(String checkstatus) {
        this.checkstatus = checkstatus;
    }

    public String getCheckinvoiceid() {
        return checkinvoiceid;
    }

    public void setCheckinvoiceid(String checkinvoiceid) {
        this.checkinvoiceid = checkinvoiceid;
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

    public String getCheckprocrecord() {
        return checkprocrecord;
    }

    public void setCheckprocrecord(String checkprocrecord) {
        this.checkprocrecord = checkprocrecord;
    }

    public String getCheckremark() {
        return checkremark;
    }

    public void setCheckremark(String checkremark) {
        this.checkremark = checkremark;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getChecktelno() {
        return checktelno;
    }

    public void setChecktelno(String checktelno) {
        this.checktelno = checktelno;
    }

    public String getCheckername() {
        return checkername;
    }

    public void setCheckername(String checkername) {
        this.checkername = checkername;
    }

    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    public String getCheckresult() {
        return checkresult;
    }

    public void setCheckresult(String checkresult) {
        this.checkresult = checkresult;
    }

    public Double getGrossprofit() {
        return grossprofit;
    }

    public void setGrossprofit(Double grossprofit) {
        this.grossprofit = grossprofit;
    }

    public String getCustomertypename() {
        return customertypename;
    }

    public void setCustomertypename(String customertypename) {
        this.customertypename = customertypename;
    }

    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }
}
