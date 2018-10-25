package com.eqy.web.pojo;
import java.io.Serializable;

/**
 * @ClassName: UserBean
 * @Description: 操作员表 T_CCS_USER
 * @author luoyb
 * @date 2017-8-2 下午5:24:08
 * 
 */
public class UserBean implements Serializable
{
    /**
     * 序列化
     */
    private static final long serialVersionUID = 4651825546133501887L;
    private String fAccountNumber;// 用户账号
    private String fAccountPassword;// 用户密码
    private String fUserNumber;// 联系电话
    private String fCity;// 所属地市名称
    private String fUserLevel;// 级别(0系统管理员;1地市操作员;2省级操作员;3地市审核员;4省级审核员)
    private String fState;// 状态(0已启用;1未启用)
    private String fCityCode;// 所属地市编号可领地市(11:苏州;12:淮安:13:宿迁;14:南京;15:连云港;16:徐州;17:常州;18:镇江;19:无锡;20:南通;21:泰州;22:盐城;23:扬州;)
    private String fFatherNumber;// 创建者
    private String fCreateTime;// 创建时间
    private String tChannelId;// 渠道编号ID
    private String roleName;// 角色名称
    private String channelName;// 渠道名称
    private String factory;// 工厂id
    private String factoryname;// 工厂name
    public String getFactoryname() {
		return factoryname;
	}
	public void setFactoryname(String factoryname) {
		this.factoryname = factoryname;
	}
	private String mail;// 邮箱号
    private String department;// 部门
    private String username;// 用户名
    
    public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
    public String getChannelName()
    {
        return channelName;
    }
    public void setChannelName(String channelName)
    {
        this.channelName = channelName;
    }
    public String getRoleName()
    {
        return roleName;
    }
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }
    public String getfAccountNumber()
    {
        return fAccountNumber;
    }
    public void setfAccountNumber(String fAccountNumber)
    {
        this.fAccountNumber = fAccountNumber == null ? null : fAccountNumber.trim();
    }
    public String getfAccountPassword()
    {
        return fAccountPassword;
    }
    public void setfAccountPassword(String fAccountPassword)
    {
        this.fAccountPassword = fAccountPassword == null ? null : fAccountPassword.trim();
    }
    public String getfUserNumber()
    {
        return fUserNumber;
    }
    public void setfUserNumber(String fUserNumber)
    {
        this.fUserNumber = fUserNumber == null ? null : fUserNumber.trim();
    }
    public String getfCity()
    {
        return fCity;
    }
    public void setfCity(String fCity)
    {
        this.fCity = fCity == null ? null : fCity.trim();
    }
    public String getfUserLevel()
    {
        return fUserLevel;
    }
    public void setfUserLevel(String fUserLevel)
    {
        this.fUserLevel = fUserLevel == null ? null : fUserLevel.trim();
    }
    public String getfState()
    {
        return fState;
    }
    public void setfState(String fState)
    {
        this.fState = fState == null ? null : fState.trim();
    }
    public String getfCityCode()
    {
        return fCityCode;
    }
    public void setfCityCode(String fCityCode)
    {
        this.fCityCode = fCityCode == null ? null : fCityCode.trim();
    }
    public String getfFatherNumber()
    {
        return fFatherNumber;
    }
    public void setfFatherNumber(String fFatherNumber)
    {
        this.fFatherNumber = fFatherNumber == null ? null : fFatherNumber.trim();
    }
    public String getfCreateTime()
    {
        return fCreateTime;
    }
    public void setfCreateTime(String fCreateTime)
    {
        this.fCreateTime = fCreateTime == null ? null : fCreateTime.trim();
    }
    public String gettChannelId()
    {
        return tChannelId;
    }
    public void settChannelId(String tChannelId)
    {
        this.tChannelId = tChannelId == null ? null : tChannelId.trim();
    }
}