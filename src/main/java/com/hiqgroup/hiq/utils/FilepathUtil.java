package com.hiqgroup.hiq.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "filepath")
public class FilepathUtil {
    private String zyUploadPath;
    private String kindEditorPath;
    private String templatePath;
    private String lucenePath;

    public String getZyUploadPath() {
        return zyUploadPath;
    }

    public void setZyUploadPath(String zyUploadPath) {
        this.zyUploadPath = zyUploadPath;
    }

    public String getKindEditorPath() {
        return kindEditorPath;
    }

    public void setKindEditorPath(String kindEditorPath) {
        this.kindEditorPath = kindEditorPath;
    }

    public String getLucenePath() {
        return lucenePath;
    }

    public void setLucenePath(String lucenePath) {
        this.lucenePath = lucenePath;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }
}
