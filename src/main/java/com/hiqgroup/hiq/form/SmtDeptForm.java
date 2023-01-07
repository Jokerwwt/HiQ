package com.hiqgroup.hiq.form;

import com.hiqgroup.hiq.entity.SmtDept;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SmtDeptForm extends SmtDept {
    /**
     * 操作员姓名
     */
    @ApiModelProperty(name = "操作员姓名")
    private String edtbyusername;

    public String getEdtbyusername() {
        return edtbyusername;
    }

    public void setEdtbyusername(String edtbyusername) {
        this.edtbyusername = edtbyusername;
    }

}
