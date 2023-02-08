package com.hiqgroup.hiq.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 检测报价表(HqtCheckprice)实体类
 *
 * @author liugaqiong
 * @since 2022-01-06 23:27:21
 */
public class HqtCheckprice implements Serializable {
    private static final long serialVersionUID = 280531575610461612L;
    /**
     * 编号
     */
    private String id;
    /**
     * 检测机构编号
     */
    private String companyid;
    /**
     * 项目名称
     */
    private String projectname;
    /**
     * 标准编号
     */
    private String standardid;
    /**
     * 标准名称
     */
    private String standardname;
    /**
     * 设备及测量范围
     */
    private String needdevice;
    /**
     * 所需样本量
     */
    private String sampleqty;
    /**
     * 待检周期
     */
    private String checkperiod;
    /**
     * 价格前缀
     */
    private String priceprefix;
    /**
     * 价格后缀
     */
    private String pricesuffix;
    /**
     * 公开价格
     */
    private Double publicprice;
    /**
     * 公开折扣
     */
    private Double discount;
    /**
     * 优惠价格
     */
    private Double privateprice;
    /**
     * 1.未收款 2.部分收款 3.已收款
     */
    private Double custratio;
    /**
     * 操作员
     */
    private String edtbyuserid;
    /**
     * 操作时间
     */
    private Date edttime;
    /**
     * 创建人
     */
    private String creator;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getStandardid() {
        return standardid;
    }

    public void setStandardid(String standardid) {
        this.standardid = standardid;
    }

    public String getStandardname() {
        return standardname;
    }

    public void setStandardname(String standardname) {
        this.standardname = standardname;
    }

    public String getNeeddevice() {
        return needdevice;
    }

    public void setNeeddevice(String needdevice) {
        this.needdevice = needdevice;
    }

    public String getSampleqty() {
        return sampleqty;
    }

    public void setSampleqty(String sampleqty) {
        this.sampleqty = sampleqty;
    }

    public String getCheckperiod() {
        return checkperiod;
    }

    public void setCheckperiod(String checkperiod) {
        this.checkperiod = checkperiod;
    }

    public String getPriceprefix() {
        return priceprefix;
    }

    public void setPriceprefix(String priceprefix) {
        this.priceprefix = priceprefix;
    }

    public String getPricesuffix() {
        return pricesuffix;
    }

    public void setPricesuffix(String pricesuffix) {
        this.pricesuffix = pricesuffix;
    }

    public Double getPublicprice() {
        return publicprice;
    }

    public void setPublicprice(Double publicprice) {
        this.publicprice = publicprice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getPrivateprice() {
        return privateprice;
    }

    public void setPrivateprice(Double privateprice) {
        this.privateprice = privateprice;
    }

    public Double getCustratio() {
        return custratio;
    }

    public void setCustratio(Double custratio) {
        this.custratio = custratio;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

}
