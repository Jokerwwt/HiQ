package com.hiqgroup.hiq.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hiqgroup.hiq.entity.HqtCustomerorder;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel
public class HqtCustomerorderForm extends HqtCustomerorder {
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
     * 收款日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date indate;
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
    private String customername;
    /**
     * 申请人姓名
     */
    private String ordermanname;
    /**
     * 负责人姓名
     */
    private String chargemanname;
    /**
     * 推荐检测单位名
     */
    private String checkersname;
    /**
     * 收款录入人姓名
     */
    private String inrecordername;
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

    public Date getHopeenddate() {
        return hopeenddate;
    }

    public void setHopeenddate(Date hopeenddate) {
        this.hopeenddate = hopeenddate;
    }

    public String getEdtbyusername() {
        return edtbyusername;
    }

    public void setEdtbyusername(String edtbyusername) {
        this.edtbyusername = edtbyusername;
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

    public String getCheckersname() {
        return checkersname;
    }

    public void setCheckersname(String checkersname) {
        this.checkersname = checkersname;
    }

    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }

    public Date getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(Date invoicedate) {
        this.invoicedate = invoicedate;
    }

    public String getInrecordername() {
        return inrecordername;
    }

    public void setInrecordername(String inrecordername) {
        this.inrecordername = inrecordername;
    }

    public String getInvoicerecordername() {
        return invoicerecordername;
    }

    public void setInvoicerecordername(String invoicerecordername) {
        this.invoicerecordername = invoicerecordername;
    }
}
