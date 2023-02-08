package com.hiqgroup.hiq.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class HqtCustStatisticForm {
    private String id;  //id
    private String statisPeriod;  //统计周期
    private int shouldRecRptTotal; //应收到报告数
    private int factRecRptTotal; //实际收到报告数
    private int noRecRptTotal;   //未收到报告数
    private int noPassRptTotal;  //不合格报告数
    private int expireRptTotal;  //到期报告数
    private double shouldPayTotal;  //应付合计
    private double factPayTotal;  //实付合计
    private double noPayTotal;    //未付合计

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
}
