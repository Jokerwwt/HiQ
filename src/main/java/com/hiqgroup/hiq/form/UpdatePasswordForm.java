package com.hiqgroup.hiq.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "登录实体")
public class UpdatePasswordForm {

    @ApiModelProperty(value = "用户")
    @NotBlank
    private String userid;

    @ApiModelProperty(value = "旧密码")
    @NotBlank
    private String oldpassword;

    @ApiModelProperty(value = "新密码")
    @NotBlank
    private String newpassword;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }
}
