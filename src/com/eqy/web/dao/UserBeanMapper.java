package com.eqy.web.dao;
import java.util.List;
import java.util.Map;
import com.eqy.web.pojo.UserBean;

/**
 * @ClassName: UserBeanMapper
 * @Description: 用户使用Mapper
 * @author luoyb
 * @date 2017-8-4 上午9:40:12
 * 
 */
public interface UserBeanMapper
{
    /**
     * @Title: deleteUserByAccountNum
     * @Description: 根据用户账号删除用户信息
     * @param @param fAccountNumber
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    int deleteUserByAccountNum(String fAccountNumber);
    /**
     * @Title: insertUser
     * @Description: 插入用户数据
     * @param @param record
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    int insertUser(UserBean record);
    /**
     * @Title: updateUser
     * @Description: 更新用户数据
     * @param @param record
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    int updateUser(UserBean record);
    /**
     * @Title: selectUserByAccountNum
     * @Description: 根据用户账号查询用户数据
     * @param @param fAccountNumber
     * @param @return 设定文件
     * @return UserBean 返回类型
     * @throws
     */
    UserBean selectUserByAccountNum(String fAccountNumber);
    /**
     * @Title: selectUserByUserNum
     * @Description: 根据用户手机号查询用户数据
     * @param @param fUserNumber
     * @param @return 设定文件
     * @return UserBean 返回类型
     * @throws
     */
    UserBean selectUserByUserNum(String fUserNumber);
    /**
     * @Title: selectUserList
     * @Description: 查询所有用户数据
     * @param @return 设定文件
     * @return List<UserBean> 返回类型
     * @throws
     */
    List<UserBean> selectUserList();
    /**
     * @Title: selectUserByCondition
     * @Description: 根据条件查询用户数据
     * @param @param map
     * @param @return 设定文件
     * @return UserBean 返回类型
     * @throws
     */
    UserBean selectUserByCondition(Map<String, Object> map);
    /**
     * @Title: selectUserByCondition
     * @Description: 根据条件模糊查询用户数据
     * @param @param map
     * @param @return 设定文件
     * @return List 返回类型
     * @throws
     */
    List<UserBean> selectUserByConditionPage(Map<String, Object> map);
    /**
     * @Title: countUserBean
     * @Description: 查询用户数量
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    int countUserBean();
    /**
     * @Title: countUserBean
     * @Description: 根据条件查询用户数量
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    int countUserBeanByCondition(Map<String, Object> map);
    /**
     * @Title: selectUserListByPage
     * @Description: 分页查询用户数据
     * @param @param map
     * @param @return 设定文件
     * @return List<UserBean> 返回类型
     * @throws
     */
    List<UserBean> selectUserListByPage(Map<String, Object> map);
    
    List<UserBean> selectAccountByLevel(int roleid);
    void UpdateRoleByLevel(int roleid);
    /**
     * @Title:
     * @Description: 查询工厂id
     * @param @param map
     * @param @return 设定文件
     * @return List<UserBean> 返回类型
     * @throws
     */
    int findFactoryNoByAccount(String Account);
    
    /**
     * @Title:
     * @Description: 查询工厂id
     * @param @param map
     * @param @return 设定文件
     * @return List<UserBean> 返回类型
     * @throws
     */
    int findAuthorityByAccount(String Account);
    
  
    
    
    
}