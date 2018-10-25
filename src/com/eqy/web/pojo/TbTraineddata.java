package com.eqy.web.pojo;

public class TbTraineddata {
    private Integer id;

    private String traineddata;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTraineddata() {
        return traineddata;
    }

    public void setTraineddata(String traineddata) {
        this.traineddata = traineddata == null ? null : traineddata.trim();
    }
}