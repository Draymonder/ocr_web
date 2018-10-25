package com.eqy.web.dao;

import com.eqy.web.pojo.TbCommonRule;
import com.eqy.web.pojo.TbRule;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbCommonRuleMapper {
    
    int deleteByPrimaryKey(Integer id);

    int insert(TbCommonRule TbCommonRule);

    TbCommonRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbCommonRule tbCommonRule);

    int updateByPrimaryKey(TbCommonRule TbCommonRule);
    
    
    int countBean(); //无条件查询总数量
    List<TbCommonRule> selectRuleListByPage(Map<String,Object> map);//无条件查询分页
    int countBeanByLike(Map<String,Object> map);//有条件查询总数量
    List<TbCommonRule> selectRuleListByPageLike(Map<String,Object> map);//有条件查询分页
    int countBeanByLikeOR(Map<String,Object> map);//查询编号和名称是否重复的方法



    

}