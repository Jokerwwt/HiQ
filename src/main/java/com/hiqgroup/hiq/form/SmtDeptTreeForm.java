package com.hiqgroup.hiq.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class SmtDeptTreeForm {
    @ApiModelProperty(name = "编号")
    private String id;
    @ApiModelProperty(name = "名称")
    private String name;
    @ApiModelProperty(name = "展开")
    private Boolean spread;
    @ApiModelProperty(name = "子节点")
    private List<SmtDeptTreeForm> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public List<SmtDeptTreeForm> getChildren() {
        return children;
    }

    public void setChildren(List<SmtDeptTreeForm> children) {
        this.children = children;
    }
}
