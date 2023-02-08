package com.hiqgroup.hiq.form;

import com.hiqgroup.hiq.entity.HqtCheckprice;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class HqtCheckPriceForm extends HqtCheckprice {

    /**
     * 操作员姓名
     */
    private String edtbyusername;

    /**
     * 单位名称
     */
    private String companyname;
    /**
     * 创建人姓名
     */
    private String creatorname;


    public String getEdtbyusername() {
        return edtbyusername;
    }

    public void setEdtbyusername(String edtbyusername) {
        this.edtbyusername = edtbyusername;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCreatorname() {
        return creatorname;
    }

    public void setCreatorname(String creatorname) {
        this.creatorname = creatorname;
    }
}
