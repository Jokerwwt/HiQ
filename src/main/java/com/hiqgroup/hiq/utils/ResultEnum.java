package com.hiqgroup.hiq.utils;


public enum ResultEnum {
    //这里是可以自己定义的，方便与前端交互即可
    UNKNOWN_ERROR(-1,"未知错误"),
    SUCCESS(200,"成功"),
    USER_NOT_EXIST(1,"用户不存在"),
    USER_NOT_CONFIRM(2,"用户已注册未审核"),
    USER_IS_INVALID(3,"用户已禁用"),
    USER_NOT_SYSTEM(4,"非系统用户"),
    PASSWORD_IS_ERROR(5,"密码输入错误"),
    USER_IS_EXISTS(6,"用户已存在"),
    DATA_IS_NULL(7,"数据为空"),
    SYSTEM_ERROR(8,"系统执行出错"),
    MOBILE_IS_EXISTS(9,"手机号码已存在"),
    TOKEN_IS_INVALID(5000,"token失效"),
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
