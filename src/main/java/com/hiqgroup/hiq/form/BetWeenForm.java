package com.hiqgroup.hiq.form;

import java.util.List;

public class BetWeenForm {
    private String fieldname;
    private List<String> fieldValues;

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public List<String> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(List<String> fieldValues) {
        this.fieldValues = fieldValues;
    }
}
