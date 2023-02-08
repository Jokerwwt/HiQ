package com.hiqgroup.hiq.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class HqtPlatformStatisticForm {
    private String id;  //id
    private String statisPeriod;    //统计周期
    private int shouldSendRptTotal;   //应发报告数
    private int factSendRptTotal;     //实发报告数
    private int noSendRptTotal;      //未发报告数
    private int noPassRptTotal;       //不合格报告数
    private int expireRptTotal;      //到期报告数
    private double shouldRecMoneyTotal;    //应收合计
    private double factRecMoneyTotal;      //实收合计
    private double noRecMoneyTotal;       //未收合计
    private double noMakeInvoiceTotal;     //未开发票合计

    private int shouldRecRptTotal;    //应收到报告数
    private int factRecRptTotal;     //实际收到报告数
    private int noRecRptTotal;       //未收到报告数
    private double shouldPayTotal;         //应付合计
    private double factPayTotal;          //实付合计
    private double noPayTotal;            //未付合计
    private double noRecInvoiceTotal;      //未收发票合计

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatisPeriod() {
        return statisPeriod;
    }

    public void setStatisPeriod(String statisPeriod) {
        this.statisPeriod = statisPeriod;
    }

    public int getShouldSendRptTotal() {
        return shouldSendRptTotal;
    }

    public void setShouldSendRptTotal(int shouldSendRptTotal) {
        this.shouldSendRptTotal = shouldSendRptTotal;
    }

    public int getFactSendRptTotal() {
        return factSendRptTotal;
    }

    public void setFactSendRptTotal(int factSendRptTotal) {
        this.factSendRptTotal = factSendRptTotal;
    }

    public int getNoSendRptTotal() {
        return noSendRptTotal;
    }

    public void setNoSendRptTotal(int noSendRptTotal) {
        this.noSendRptTotal = noSendRptTotal;
    }

    public int getNoPassRptTotal() {
        return noPassRptTotal;
    }

    public void setNoPassRptTotal(int noPassRptTotal) {
        this.noPassRptTotal = noPassRptTotal;
    }

    public int getExpireRptTotal() {
        return expireRptTotal;
    }

    public void setExpireRptTotal(int expireRptTotal) {
        this.expireRptTotal = expireRptTotal;
    }

    public double getShouldRecMoneyTotal() {
        return shouldRecMoneyTotal;
    }

    public void setShouldRecMoneyTotal(double shouldRecMoneyTotal) {
        this.shouldRecMoneyTotal = shouldRecMoneyTotal;
    }

    public double getFactRecMoneyTotal() {
        return factRecMoneyTotal;
    }

    public void setFactRecMoneyTotal(double factRecMoneyTotal) {
        this.factRecMoneyTotal = factRecMoneyTotal;
    }

    public double getNoRecMoneyTotal() {
        return noRecMoneyTotal;
    }

    public void setNoRecMoneyTotal(double noRecMoneyTotal) {
        this.noRecMoneyTotal = noRecMoneyTotal;
    }

    public double getNoMakeInvoiceTotal() {
        return noMakeInvoiceTotal;
    }

    public void setNoMakeInvoiceTotal(double noMakeInvoiceTotal) {
        this.noMakeInvoiceTotal = noMakeInvoiceTotal;
    }

    public int getShouldRecRptTotal() {
        return shouldRecRptTotal;
    }

    public void setShouldRecRptTotal(int shouldRecRptTotal) {
        this.shouldRecRptTotal = shouldRecRptTotal;
    }

    public int getFactRecRptTotal() {
        return factRecRptTotal;
    }

    public void setFactRecRptTotal(int factRecRptTotal) {
        this.factRecRptTotal = factRecRptTotal;
    }

    public int getNoRecRptTotal() {
        return noRecRptTotal;
    }

    public void setNoRecRptTotal(int noRecRptTotal) {
        this.noRecRptTotal = noRecRptTotal;
    }

    public double getShouldPayTotal() {
        return shouldPayTotal;
    }

    public void setShouldPayTotal(double shouldPayTotal) {
        this.shouldPayTotal = shouldPayTotal;
    }

    public double getFactPayTotal() {
        return factPayTotal;
    }

    public void setFactPayTotal(double factPayTotal) {
        this.factPayTotal = factPayTotal;
    }

    public double getNoPayTotal() {
        return noPayTotal;
    }

    public void setNoPayTotal(double noPayTotal) {
        this.noPayTotal = noPayTotal;
    }

    public double getNoRecInvoiceTotal() {
        return noRecInvoiceTotal;
    }

    public void setNoRecInvoiceTotal(double noRecInvoiceTotal) {
        this.noRecInvoiceTotal = noRecInvoiceTotal;
    }
}
