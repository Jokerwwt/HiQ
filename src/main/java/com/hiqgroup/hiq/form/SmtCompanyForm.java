package com.hiqgroup.hiq.form;

import com.hiqgroup.hiq.entity.SmtCompany;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class SmtCompanyForm extends SmtCompany {
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
