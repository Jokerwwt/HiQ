package com.hiqgroup.hiq.entity;

import java.io.Serializable;

/**
 * 单据附件表(HqtOrderattach)实体类
 *
 * @author liugaqiong
 * @since 2022-01-01 10:01:58
 */
public class HqtOrderattach implements Serializable {
    private static final long serialVersionUID = -50965653421989691L;
    /**
     * 编号
     */
    private String id;
    /**
     * 单据编号
     */
    private String orderid;
    /**
     * 附件名称
     */
    private String filename;
    /**
     * 附件路径
     */
    private String filepath;
    /**
     * 1.附件文件  2.报告
     */
    private String filetype;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

}
