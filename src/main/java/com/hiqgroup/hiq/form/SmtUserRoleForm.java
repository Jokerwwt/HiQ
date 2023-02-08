package com.hiqgroup.hiq.form;

public class SmtUserRoleForm {

    /**
     * 角色编号
     */
    private String id;
    /**
     * 角色名称
     */
    private String rolename;
    /**
     * 是否有权限
     */
    private boolean ischeck;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public boolean isIscheck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

}
