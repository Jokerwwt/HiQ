package com.hiqgroup.hiq.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class HqtCheckerStatisticForm {
    private String id;  //id
    private String statisPeriod;  //统计周期
    private int shouldRecOrderTotal;   //应收到订单数
    private double shouldRecOrderAmtTotal;  //应收订单金额合计
    private int factRecOrderTotal;    //实收到订单数
    private double factRecOrderAmtTotal;   //实收订单金额合计
    private int noRecOrderTotal;       //未收到订单数
    private double noRecOrderAmtTotal;     //未收订单金额合计
    private int shouldSendRptTotal;    //应发报告数
    private int factSendRptTotal;      //实发报告数
    private int noSendRptTotal;       //未发报告数

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

    public int getShouldRecOrderTotal() {
        return shouldRecOrderTotal;
    }

    public void setShouldRecOrderTotal(int shouldRecOrderTotal) {
        this.shouldRecOrderTotal = shouldRecOrderTotal;
    }

    public double getShouldRecOrderAmtTotal() {
        return shouldRecOrderAmtTotal;
    }

    public void setShouldRecOrderAmtTotal(double shouldRecOrderAmtTotal) {
        this.shouldRecOrderAmtTotal = shouldRecOrderAmtTotal;
    }

    public int getFactRecOrderTotal() {
        return factRecOrderTotal;
    }

    public void setFactRecOrderTotal(int factRecOrderTotal) {
        this.factRecOrderTotal = factRecOrderTotal;
    }

    public double getFactRecOrderAmtTotal() {
        return factRecOrderAmtTotal;
    }

    public void setFactRecOrderAmtTotal(double factRecOrderAmtTotal) {
        this.factRecOrderAmtTotal = factRecOrderAmtTotal;
    }

    public int getNoRecOrderTotal() {
        return noRecOrderTotal;
    }

    public void setNoRecOrderTotal(int noRecOrderTotal) {
        this.noRecOrderTotal = noRecOrderTotal;
    }

    public double getNoRecOrderAmtTotal() {
        return noRecOrderAmtTotal;
    }

    public void setNoRecOrderAmtTotal(double noRecOrderAmtTotal) {
        this.noRecOrderAmtTotal = noRecOrderAmtTotal;
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
}
