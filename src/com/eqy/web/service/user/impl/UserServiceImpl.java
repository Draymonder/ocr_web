package com.eqy.web.service.user.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eqy.web.dao.TNgcFactoryMapper;
import com.eqy.web.dao.UserBeanMapper;
import com.eqy.web.dao.UserPermissionBeanMapper;
import com.eqy.web.pojo.PermissionBean;
import com.eqy.web.pojo.UserBean;
import com.eqy.web.service.user.IUserService;


@Service("userService")
public class UserServiceImpl implements IUserService
{
	@Autowired
    protected UserBeanMapper userBeanMapper;
	@Autowired
    protected TNgcFactoryMapper tNgcFactoryMapper;
    @Autowired
    protected UserPermissionBeanMapper userPermissionBeanMapper;
	@Override
	public UserBean selectUserByAccountNum(String fAccountNumber) {
		// TODO Auto-generated method stub
		
		return this.userBeanMapper.selectUserByAccountNum(fAccountNumber);
	}




	@Override
	public List<PermissionBean> selectPermListByAccount(String fAccountNumber) {
		// TODO Auto-generated method stub
        return this.userPermissionBeanMapper.selectPermListByAccount(fAccountNumber);
	}




	@Override
	public String selectFactoryName(String fAccountNumber) {
		int factoryid=userBeanMapper.findFactoryNoByAccount(fAccountNumber);
		String factoryName=tNgcFactoryMapper.selectByPrimaryKey(factoryid).getName();
		
		return factoryName;
	}




	@Override
	public int selectAutority(String fAccountNumber) {
		int sl=0;
		try{
		 sl=userBeanMapper.findAuthorityByAccount(fAccountNumber);
		//String factoryName=tNgcFactoryMapper.selectByPrimaryKey(factoryid).getName();
		}catch (Exception e)
	     {
            e.printStackTrace();
            
        }
		return sl;
	}
    

}
