package com.eqy.web.pojo;

import java.util.Date;

public class TbCommonTask {
    private Integer id;

    private String taskNumber;

    private String creator;

    private Date createTime;

    private String status;

    private Integer factoryId;

    private Date finishTime;
    
    private String  simpleTime;
    private String factory_name;
    private String  stringFinishTime;

    public Integer getId() {
        return id;
    }

    public String getSimpleTime() {
		return simpleTime;
	}

	public void setSimpleTime(String simpleTime) {
		this.simpleTime = simpleTime;
	}

	public String getFactory_name() {
		return factory_name;
	}

	public void setFactory_name(String factory_name) {
		this.factory_name = factory_name;
	}

	public String getStringFinishTime() {
		return stringFinishTime;
	}

	public void setStringFinishTime(String stringFinishTime) {
		this.stringFinishTime = stringFinishTime;
	}

	public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber == null ? null : taskNumber.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}