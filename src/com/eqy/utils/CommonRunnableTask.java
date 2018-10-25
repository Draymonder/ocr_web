package com.eqy.utils;

import com.eqy.Tess4j.Tess4j;
import com.eqy.constants.SystemConstants;
import com.eqy.web.dao.TCommonRuleMapperMapper;
import com.eqy.web.dao.TNgcDataCommonMapper;
import com.eqy.web.dao.TbCommonDataMarkMapper;
import com.eqy.web.dao.TbCommonRuleMapper;
import com.eqy.web.pojo.TbCommonDataMark;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chengkang
 * 2018/7/24 下午12:53
 */
public class CommonRunnableTask {
    private TCommonRuleMapperMapper tCommonRuleMapperMapper;
    private TbCommonDataMarkMapper tbCommonDataMarkMapper;
    private TNgcDataCommonMapper tNgcDataCommonMapper;
    private int x;
    private int y;
    private int width;
    private int height;
    private String file_src;
    private String sub_src;
    private int templateID;
    private String traineddata;

    public TCommonRuleMapperMapper gettCommonRuleMapperMapper() {
        return tCommonRuleMapperMapper;
    }

    public void settCommonRuleMapperMapper(TCommonRuleMapperMapper tCommonRuleMapperMapper) {
        this.tCommonRuleMapperMapper = tCommonRuleMapperMapper;
    }

    public TbCommonDataMarkMapper getTbCommonDataMarkMapper() {
        return tbCommonDataMarkMapper;
    }

    public void setTbCommonDataMarkMapper(TbCommonDataMarkMapper tbCommonDataMarkMapper) {
        this.tbCommonDataMarkMapper = tbCommonDataMarkMapper;
    }

    public TNgcDataCommonMapper gettNgcDataCommonMapper() {
        return tNgcDataCommonMapper;
    }

    public void settNgcDataCommonMapper(TNgcDataCommonMapper tNgcDataCommonMapper) {
        this.tNgcDataCommonMapper = tNgcDataCommonMapper;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFile_src() {
        return file_src;
    }

    public void setFile_src(String file_src) {
        this.file_src = file_src;
    }

    public String getSub_src() {
        return sub_src;
    }

    public void setSub_src(String sub_src) {
        this.sub_src = sub_src;
    }

    public int getTemplateID() {
        return templateID;
    }

    public void setTemplateID(int templateID) {
        this.templateID = templateID;
    }

    public String getTraineddata() {
        return traineddata;
    }

    public void setTraineddata(String traineddata) {
        this.traineddata = traineddata;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    private Map<String, Object> map;
    public CommonRunnableTask(){}
    public CommonRunnableTask(int x, int y , int width, int height,
                              String file_src, String sub_src,Map<String, Object> map,
                              int templateID, String traineddata,
                              TCommonRuleMapperMapper tCommonRuleMapperMapper,
                              TbCommonDataMarkMapper tbCommonDataMarkMapper,
                              TNgcDataCommonMapper tNgcDataCommonMapper){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.file_src = file_src;
        this.sub_src = sub_src;
        this.map = map;
        this.templateID = templateID;
        this.traineddata = traineddata;
        this.tCommonRuleMapperMapper = tCommonRuleMapperMapper;
        this.tbCommonDataMarkMapper = tbCommonDataMarkMapper;
        this.tNgcDataCommonMapper = tNgcDataCommonMapper;
    }
    public void run() {
        try {
            //new OperateImg(x, y, width, height, this.file_src, this.sub_src).cut();
            String suffix = newOperateImg.OperateImgCut(x, y, width, height, this.file_src, this.sub_src);
            String result = Tess4j.covert(this.sub_src + suffix, this.traineddata);
            //查询所有与该字段匹配的规则
            Map<String, Object> ruleMap = new HashMap<>();
            ruleMap.put("id", this.templateID);
            ruleMap.put("fieldName", this.map.get("fieldName"));
            List<Map<String, Object>> list = tCommonRuleMapperMapper.selectByFieldNameANDTemplateID(ruleMap);//查询字段所有关联规则
            int count1 = 0, count2 = 0;
            int data_id = Integer.parseInt(this.map.get("id").toString());
            if(!list.isEmpty()){
                StringBuilder description = new StringBuilder();
                for(Map<String, Object> me : list){
                    switch(me.get("coverType").toString()){
                        case SystemConstants.RULE_TYPE_FORCE:
                            String resultA = RuleUtil.convertByForce(result, me.get("flag_A").toString(), me.get("flag_B").toString());
                            if (!result.equals(resultA)) {
                                result = resultA;
                                description.append(me.get("rule_name")).append("|");
                                count1++;
                            }
                            break;
                        case SystemConstants.RULE_TYPE_TIP:
                            if (!RuleUtil.tipRule(result, me.get("flag_A").toString().split(","))){
                                description.append(me.get("rule_name")).append("|");
                                count2++;
                            }
                            break;
                    }
                }
                if(description.length() != 0){
                    TbCommonDataMark tbCommonDataMark = new TbCommonDataMark();
                    if(count2 > 0){
                        tbCommonDataMark.setFlag(SystemConstants.flag_DataMark_error);//如果存在提示规则,置位1,后期标红
                    }
                    else if(count1 > 0){
                        tbCommonDataMark.setFlag(SystemConstants.flag_DataMark_fine);//如果只有强转规则,置位0,后期标黄
                    }
                    tbCommonDataMark.setDataId(data_id);
                    tbCommonDataMark.setDescription(description.toString());
                    tbCommonDataMark.setFieldname(this.map.get("fieldName").toString());
                    tbCommonDataMarkMapper.insertSelective(tbCommonDataMark);
                }
            }
            this.map.put("result", result);
            tNgcDataCommonMapper.updateData(this.map);//结果入库

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
