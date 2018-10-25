package com.eqy.web.pojo;

import java.util.Date;

public class TbRule {
    private Integer id;

    private String ruleNumber;//规则编号

    private String ruleName;//规则名称

    private String covertype;//类型

    private Date date;//日期

    private String accounctNumber;//账号

    private Integer status;//状态 0启用，1弃用
    
    private String flagA;//字段A
    
    private String flagB;//字段B
    
	private String simpleDate;//日期
    
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
		this.flagA = flagA;
	}

	public String getFlagB() {
		return flagB;
	}

	public void setFlagB(String flagB) {
		this.flagB = flagB;
	}




    public String getSimpleDate() {
		return simpleDate;
	}

	public void setSimpleDate(String simpleDate) {
		this.simpleDate = simpleDate;
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

    
}