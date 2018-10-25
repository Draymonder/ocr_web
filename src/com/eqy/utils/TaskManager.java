package com.eqy.utils;

import com.eqy.Tess4j.Tess4j;
import com.eqy.constants.SystemConstants;
import com.eqy.tools.SpringUtil;
import com.eqy.web.dao.TRuleMapperMapper;
import com.eqy.web.dao.TbDataMarkMapper;
import com.eqy.web.dao.TemplateSearchMapper;
import com.eqy.web.pojo.TbDataMark;
import com.eqy.web.service.task.ITaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by chengkang
 * 2018/6/13 上午9:14
 */
public class TaskManager {
    private static Logger log = LoggerFactory.getLogger(TaskManager.class);
    ITaskService iTaskService = (ITaskService) SpringUtil.getBean("taskService");
    private TRuleMapperMapper tRuleMapperMapper;
    private TemplateSearchMapper templateSearchMapper;
    private TbDataMarkMapper tbDataMarkMapper;

    private Map<String, Object> map;
    private int x;
    private int y;
    private int width;
    private int height;
    private String file_src;
    private String sub_src;//子图资源路径


    public String getSub_src() {
        return sub_src;
    }

    public void setSub_src(String sub_src) {
        this.sub_src = sub_src;
    }


    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
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


    public TaskManager(){}

    public TaskManager(TbDataMarkMapper tbDataMarkMapper,
                       TRuleMapperMapper tRuleMapperMapper,
                       TemplateSearchMapper templateSearchMapper,
                       int x, int y, int width, int height,
                       String file_src, String sub_src,
                       Map<String, Object> map){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.file_src = file_src;
        this.sub_src = sub_src;
        this.map = map;
        this.templateSearchMapper = templateSearchMapper;
        this.tRuleMapperMapper = tRuleMapperMapper;
        this.tbDataMarkMapper = tbDataMarkMapper;
    }

    public void run() {
        try {
            new OperateImg(x, y, width, height, this.file_src, this.sub_src).cut();
            String result = Tess4j.covert(this.sub_src,"num");//识别结果
            StringBuilder description = new StringBuilder();//描述语
            List<Map<String, Object>> list = tRuleMapperMapper.selectByFieldname(this.map.get("fieldName").toString());//查询字段所有关联规则
            int count1 = 0, count2 = 0;//记录两种规则标注
            int data_id = Integer.parseInt(this.map.get("id").toString());
            if(!list.isEmpty()){
                for(Map<String, Object> me : list){
                    if (me.get("coverType").equals(SystemConstants.RULE_TYPE_FORCE)) {
                        String resultA = RuleUtil.convertByForce(result, me.get("flag_A").toString(), me.get("flag_B").toString() );
                        if (!result.equals(resultA)) {
                            result = resultA;
                            description.append(me.get("rule_name")).append("|");
                            count1++;
                        }
                    }
                    else {
                        if (!RuleUtil.tipRule(result, me.get("flag_A").toString().split(","))){
                            description.append(me.get("rule_name")).append("|");
                            count2++;
                        }
                    }
                }

                if(description.length() != 0){
                    TbDataMark tbDataMark = new TbDataMark();
                    if(count2 > 0){
                        tbDataMark.setFlag(SystemConstants.flag_DataMark_error);//如果存在提示规则,置位1,后期标红
                    }
                    else if(count1 > 0){
                        tbDataMark.setFlag(SystemConstants.flag_DataMark_fine);//如果只有强转规则,置位0,后期标黄
                    }
                    tbDataMark.setDataId(data_id);
                    tbDataMark.setDescription(description.toString());
                    tbDataMark.setFieldname(this.map.get("fieldName").toString());
                    this.tbDataMarkMapper.insertSelective(tbDataMark);
                }
            }
            this.map.put("result", result);
           // log.error("开始入库》》》》数据id="+data_id+"字段: "+this.map.get("fieldName").toString()+"   result=  "+result);
            this.templateSearchMapper.updateData(this.map);//结果入库
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
