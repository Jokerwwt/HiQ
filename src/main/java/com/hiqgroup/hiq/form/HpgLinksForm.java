package com.hiqgroup.hiq.form;

import com.hiqgroup.hiq.entity.HpgLinks;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class HpgLinksForm extends HpgLinks {
    private String edtbyusername;

    public String getEdtbyusername() {
        return edtbyusername;
    }

    public void setEdtbyusername(String edtbyusername) {
        this.edtbyusername = edtbyusername;
    }
}
