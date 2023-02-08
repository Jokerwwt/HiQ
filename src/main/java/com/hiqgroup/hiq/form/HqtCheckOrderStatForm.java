package com.hiqgroup.hiq.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class HqtCheckOrderStatForm {
    private int todayAppendTotal;    //今日新增订单数
    private int willAcceptTotal;     //待接单数
    private int todayFinishTotal;    //今日已完成订单数
    private int orderTotal;          //总订单数
    private int checkingTotal;       //正在检测订单数
    private int checkNopassTotal;    //检测不合格订单数

    public int getTodayAppendTotal() {
        return todayAppendTotal;
    }

    public void setTodayAppendTotal(int todayAppendTotal) {
        this.todayAppendTotal = todayAppendTotal;
    }

    public int getWillAcceptTotal() {
        return willAcceptTotal;
    }

    public void setWillAcceptTotal(int willAcceptTotal) {
        this.willAcceptTotal = willAcceptTotal;
    }

    public int getTodayFinishTotal() {
        return todayFinishTotal;
    }

    public void setTodayFinishTotal(int todayFinishTotal) {
        this.todayFinishTotal = todayFinishTotal;
    }

    public int getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(int orderTotal) {
        this.orderTotal = orderTotal;
    }

    public int getCheckingTotal() {
        return checkingTotal;
    }

    public void setCheckingTotal(int checkingTotal) {
        this.checkingTotal = checkingTotal;
    }

    public int getCheckNopassTotal() {
        return checkNopassTotal;
    }

    public void setCheckNopassTotal(int checkNopassTotal) {
        this.checkNopassTotal = checkNopassTotal;
    }
}
