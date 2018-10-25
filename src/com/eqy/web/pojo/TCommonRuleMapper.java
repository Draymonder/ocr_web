package com.eqy.web.pojo;

public class TCommonRuleMapper {
    private Integer ruleId;



    private String Acolumn;

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    

    public String getAcolumn() {
        return Acolumn;
    }

    public void setAcolumn(String Acolumn) {
        this.Acolumn = Acolumn == null ? null : Acolumn.trim();
    }
}