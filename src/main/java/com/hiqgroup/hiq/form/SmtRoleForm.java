package com.hiqgroup.hiq.form;

import com.hiqgroup.hiq.entity.SmtRole;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class SmtRoleForm extends SmtRole {
    /**
     * 操作员姓名
     */
    private String edtbyusername;

    public String getEdtbyusername() {
        return edtbyusername;
    }

    public void setEdtbyusername(String edtbyusername) {
        this.edtbyusername = edtbyusername;
    }
}
