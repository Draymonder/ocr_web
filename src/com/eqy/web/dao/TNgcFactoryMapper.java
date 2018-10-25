package com.eqy.web.dao;

import com.eqy.web.pojo.TNgcFactory;
import java.util.List;
import java.util.Map;



public interface TNgcFactoryMapper {
   

    int deleteByPrimaryKey(Integer id);

    int insert(TNgcFactory record);

    int insertSelective(TNgcFactory record);

    

    TNgcFactory selectByPrimaryKey(Integer id);

    

    int updateByPrimaryKeySelective(TNgcFactory record);

    int updateByPrimaryKey(TNgcFactory record);
    
    /**
     * @Title: countFactoryBean
     * @Description: 查询工厂数量
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    int countFactoryBean();
    /**
     * @Title: selectFactoryListByPage
     * @Description: 分页查询工厂数据
     * @param @param map
     * @param @return 设定文件
     * @return List<UserBean> 返回类型
     * @throws
     */
     List<TNgcFactory> selectFactoryListByPage(Map<String, Object> map);
     /**
      * @Title: selectCountByID(factory增加修改时使用）
      * @Description: 
      * @param @param id
      * @param @return 查重
      * @return List<UserBean> 返回类型
      * @throws
      */
     int selectCountByID(Map<String, Object> map);
     
     /**
      * @Title: selectCountByIDLike(factory模糊查询时使用）
      * @Description: 
      * @param @param id
      * @param @return 查重
      * @return List<UserBean> 返回类型
      * @throws
      */
     int selectCountByIDLike(Map<String, Object> map);
     
     /**
      * @Title: selectfactoryByConditionPage
      * @Description: 
      * @param @param map
      * @param @return 分页查询
      * @return List<UserBean> 返回类型
      * @throws
      */
     List<TNgcFactory> selectfactoryByConditionPage(Map<String, Object> map);
     
     List<TNgcFactory> selectAll();
     
}