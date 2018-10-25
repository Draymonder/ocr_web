package com.eqy.web.dao;

import com.eqy.web.pojo.TbRule;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TbRuleMapper {
 

    int deleteByPrimaryKey(Integer id);

    int insert(TbRule record);

    TbRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbRule record);

    int updateByPrimaryKey(TbRule record);
    
    
    int countBean(); //无条件查询总数量
    List<TbRule> selectRuleListByPage(Map<String,Object> map);//无条件查询分页
    int countBeanByLike(Map<String,Object> map);//有条件查询总数量
    List<TbRule> selectRuleListByPageLike(Map<String,Object> map);//有条件查询分页
    int countBeanByLikeOR(Map<String,Object> map);//查询编号和名称是否重复的方法
    
    int selectMaxId(); //查询刚插入的数据ID
    
}