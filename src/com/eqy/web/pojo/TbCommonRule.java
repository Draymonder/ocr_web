package com.eqy.web.pojo;

import java.util.Date;

public class TbCommonRule {
    private Integer id;

    private String ruleNumber;

    private String ruleName;

    private String covertype;

    private Date date;

    private String accounctNumber;

    private Integer status;

    private String flagA;

    private String flagB;
    
   // private Integer Template_ID;
    
    private Integer templateId;
    
    private String simpleDate;
    
    private String  templateName;
    
    
    
    public String getSimpleDate() {
		return simpleDate;
	}

	public void setSimpleDate(String simpleDate) {
		this.simpleDate = simpleDate;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

    

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuleNumber() {
        return ruleNumber;
    }

    public void setRuleNumber(String ruleNumber) {
        this.ruleNumber = ruleNumber == null ? null : ruleNumber.trim();
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public String getCovertype() {
        return covertype;
    }

    public void setCovertype(String covertype) {
        this.covertype = covertype == null ? null : covertype.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAccounctNumber() {
        return accounctNumber;
    }

    public void setAccounctNumber(String accounctNumber) {
        this.accounctNumber = accounctNumber == null ? null : accounctNumber.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFlagA() {
        return flagA;
    }

    public void setFlagA(String flagA) {
        this.flagA = flagA == null ? null : flagA.trim();
    }

    public String getFlagB() {
        return flagB;
    }

    public void setFlagB(String flagB) {
        this.flagB = flagB == null ? null : flagB.trim();
    }
}