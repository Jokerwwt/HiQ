package com.hiqgroup.hiq.form;

public class SmtMenuRightitemForm {
    /**
     * 菜单编号
     */
    private String menuid;
    /**
     * 功能编号
     */
    private Integer itemid;
    /**
     * 功能名称
     */
    private String itemname;
    /**
     * 是否有权限
     */
    private boolean ischeck;

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public boolean isIscheck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }
}
