package com.hiqgroup.hiq.form;

import com.hiqgroup.hiq.entity.HpgDaily;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class HpgReshowDailyForm extends HpgDaily {
    private String imageurl;

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
