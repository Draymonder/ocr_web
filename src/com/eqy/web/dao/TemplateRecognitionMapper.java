package com.eqy.web.dao;

import java.util.List;
import java.util.Map;

import com.eqy.web.pojo.TemplateRecognition;

/**
 * Created by chengkang
 * 2018/6/20 上午10:07
 */
public interface TemplateRecognitionMapper {
    List<Map<String, Object>> findMapList();
    int CountBean(String templateName);
    void insertTemplate(Map<String, Object> map);
    void updateRecognitionTemplateName(Map<String, Object> map);//修改模板名称时，把模板识别表模板名称更新
    void removeTemplate(String templateName);
}
