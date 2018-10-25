package com.eqy.web.dao;

import com.eqy.web.pojo.TNgcMail;

public interface TNgcMailMapper {
   
    int insert(TNgcMail record);

    int insertSelective(TNgcMail record);


    /**
     * @Title: selectOneByOne
     * @Description: 
     * @param @param 
     * @param @return 找出系统邮件服务器mail的设置数据
     * @return TNgcMail 返回类型
     * @throws
     */
    TNgcMail selectOneByOne();
    
    /**
     * @Title: update
     * @Description: 
     * @param @param 
     * @param @return updatemail的设置数据
     * @return TNgcMail 返回类型
     * @throws
     */
    void update(TNgcMail mail);
}