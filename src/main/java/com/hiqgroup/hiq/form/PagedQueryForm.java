package com.hiqgroup.hiq.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页查询的实体封装类
 * @param <T>
 */
@ApiModel
public class PagedQueryForm<T> {

    @ApiModelProperty(name = "当前页面")
    private int page;

    @ApiModelProperty(name = "每页记录数")
    private int limit;

    @ApiModelProperty(name = "排序的字段")
    private String sort;

    @ApiModelProperty(name = "排序的方式")
    private String ordertype;

    @ApiModelProperty(name = "条件查询的条件表单")
    private T searchList;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public T getSearchList() {
        return searchList;
    }

    public void setSearchList(T searchList) {
        this.searchList = searchList;
    }
}
