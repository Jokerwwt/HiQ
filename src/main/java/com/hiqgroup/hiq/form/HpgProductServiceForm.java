package com.hiqgroup.hiq.form;

import com.hiqgroup.hiq.entity.HpgProductService;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class HpgProductServiceForm extends HpgProductService {
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
