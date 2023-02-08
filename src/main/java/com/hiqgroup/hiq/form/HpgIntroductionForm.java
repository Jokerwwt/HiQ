package com.hiqgroup.hiq.form;

import com.hiqgroup.hiq.entity.HpgIntroduction;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class HpgIntroductionForm extends HpgIntroduction {
    private String writername;
    private String publishername;
    private String edtbyusername;

    public String getWritername() {
        return writername;
    }

    public void setWritername(String writername) {
        this.writername = writername;
    }

    public String getPublishername() {
        return publishername;
    }

    public void setPublishername(String publishername) {
        this.publishername = publishername;
    }

    public String getEdtbyusername() {
        return edtbyusername;
    }

    public void setEdtbyusername(String edtbyusername) {
        this.edtbyusername = edtbyusername;
    }
}
