package com.hiqgroup.hiq.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "注册实体")
public class RegisteForm {

    @ApiModelProperty(value = "用户账号")
    @NotBlank
    private String userid;
    @ApiModelProperty(value = "用户姓名")
    @NotBlank
    private String username;
    @ApiModelProperty(value = "用户类型")
    @NotBlank
    private String companytype;
    @ApiModelProperty(value = "手机号码")
    @NotBlank
    private String mobile;
    @ApiModelProperty(value = "密码")
    @NotBlank
    private String password;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanytype() {
        return companytype;
    }

    public void setCompanytype(String companytype) {
        this.companytype = companytype;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
