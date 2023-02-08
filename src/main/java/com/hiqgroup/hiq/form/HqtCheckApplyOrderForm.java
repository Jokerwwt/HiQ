package com.hiqgroup.hiq.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hiqgroup.hiq.entity.HqtCheckapplyorder;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel
public class HqtCheckApplyOrderForm extends HqtCheckapplyorder {

    /**
     * 申请单日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date orderdate;
    /**
     * 期望完成日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date hopeenddate;
    /**
     * 操作员姓名
     */
    private String edtbyusername;
    /**
     * 客户名称
     */
    private String customername;
    /**
     * 接单业务员姓名
     */
    private String acceptername;
    /**
     * 申请人姓名
     */
    private String ordermanname;
    /**
     * 推荐检测单位名
     */
    private String checkersname;
    /**
     * 推荐检测单位名
     */
    private String tocompanyname;


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

    public String getAcceptername() {
        return acceptername;
    }

    public void setAcceptername(String acceptername) {
        this.acceptername = acceptername;
    }

    public String getOrdermanname() {
        return ordermanname;
    }

    public void setOrdermanname(String ordermanname) {
        this.ordermanname = ordermanname;
    }

    public String getCheckersname() {
        return checkersname;
    }

    public void setCheckersname(String checkersname) {
        this.checkersname = checkersname;
    }

    public String getTocompanyname() {
        return tocompanyname;
    }

    public void setTocompanyname(String tocompanyname) {
        this.tocompanyname = tocompanyname;
    }
}
