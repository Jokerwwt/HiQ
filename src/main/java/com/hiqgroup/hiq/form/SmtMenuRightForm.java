package com.hiqgroup.hiq.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class SmtMenuRightForm {
    /**
     * 菜单编号
     */
    private String id;
    /**
     * 上级菜单编号
     */
    private String parentid;
    /**
     * 菜单名称
     */
    private String menuname;
    /**
     * 权限值
     */
    private String rightvalue;
    /**
     * 权限项
     */
    private List<SmtMenuRightitemForm> smtMenuRightitems;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getRightvalue() {
        return rightvalue;
    }

    public void setRightvalue(String rightvalue) {
        this.rightvalue = rightvalue;
    }

    public List<SmtMenuRightitemForm> getSmtMenuRightitems() {
        return smtMenuRightitems;
    }

    public void setSmtMenuRightitems(List<SmtMenuRightitemForm> smtMenuRightitems) {
        this.smtMenuRightitems = smtMenuRightitems;
    }
}
