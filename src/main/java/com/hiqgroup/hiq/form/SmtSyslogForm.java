package com.hiqgroup.hiq.form;

import com.hiqgroup.hiq.entity.SmtSyslog;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class SmtSyslogForm extends SmtSyslog {
    /**
     * 用户姓名
     */
    private String username;
    /**
     * 菜单名称
     */
    private String menuname;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }
}
