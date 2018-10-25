package com.eqy.web.service.templatesearch.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eqy.constants.SystemConstants;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;
import com.eqy.tools.MapUtil;

import javax.servlet.http.HttpSession;

import com.eqy.tools.MapUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.eqy.web.pojo.Pagenation;
import com.eqy.web.pojo.ShowDateBean;
import com.eqy.web.pojo.TNgcData;
import com.eqy.web.pojo.TemplateBean;
import com.eqy.web.service.templatesearch.ItemplateSearchService;
import com.eqy.utils.DictionaryMethod;
import com.eqy.web.dao.FactoryBeanMapper;
import com.eqy.web.dao.TbDataMarkMapper;
import com.eqy.web.dao.TbMapMapper;
import com.eqy.web.dao.TemplateSearchMapper;
import com.eqy.web.dao.UserBeanMapper;

@Service("templateSearchService")
public class templateSearchServiceImp implements ItemplateSearchService {
    @Autowired
    private TemplateSearchMapper TemplateSearchMapper;
    @Autowired
    private UserBeanMapper UserBeanMapper;
    @Autowired
    private FactoryBeanMapper FactoryBeanMapper;
    
    @Autowired
    protected TbDataMarkMapper  TbDataMark;
    @Autowired
    protected TbMapMapper TbMapMapper;

    
	@Override
	public void updateData(Map<String, Object> map) {
		TemplateSearchMapper.updateData(map);
		
	}

	@Override
	public void showTemplateSearchManage(HttpServletRequest request, Model model,HttpSession session) {
		 Map<String, Object> map = new HashMap<String, Object>();
         Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
         String qx = request.getParameter("qx");//qx为0代表显示自己的待办数据，否则去掉此条件
         String flag2 = request.getParameter("flag2");//是否显示异常紧急数据(错误纠正)
         Integer size = DictionaryMethod.size;
         int sl=(int) request.getSession().getAttribute("SearchAuthority");
         String  account_number=(String) request.getSession().getAttribute("User");
         map.put("Authority",sl);
         map.put("qx",qx);
         map.put("flag2",flag2);
         map.put("account_number",account_number);
         map.put("flag2",flag2);
         Integer count =TemplateSearchMapper.countBean(map);
         Pagenation page = new Pagenation(pageNum, size, count);
         map.put("start", page.getStartRow());
         map.put("size", page.getSize());
         try{
         List<TNgcData> list =TemplateSearchMapper.selectTaskListByPage(map);
         System.out.println("数量list:"+list.size());
         for(TNgcData TNgcData:list){
        	 if(TNgcData.getAccountNumber()!=null){
        		  //通过账号查询到用户名,工厂名
        		 System.out.println("用户名"+TNgcData.getAccountNumber());
        		 String factoryID=UserBeanMapper.selectUserByAccountNum(TNgcData.getAccountNumber()).getFactory();
        		 System.out.println("工厂名"+factoryID);
        		 if(!factoryID.equals("")){
        			 TNgcData.setFactoryName(FactoryBeanMapper.findFactoryNameByFactoryNo(Integer.parseInt(factoryID)));
        		 }
        		 TNgcData.setUserName(UserBeanMapper.selectUserByAccountNum(TNgcData.getAccountNumber()).getUsername());
        		 TNgcData.setSimpleTime(TemplateBean.DateChange(TNgcData.getCreateTime()));
        		 
        	 }
        	 
         }
         page.setList(list);
         model.addAttribute("page", page);
         } catch (Exception e)
         {
             e.printStackTrace();
         }
		
	}

	@Override
	public Map<String, Object> executeQueryByCondition(HttpServletRequest request) {
    	String msg = "";// 错误信息
        String resultCode = "1";
        String date = request.getParameter("date");
        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<TNgcData> list1=null;
    	int sl=0;
    	try { 
    		//returnMap = userManageService.executeSelectUserByCondition(request);
    		    sl=(int) request.getSession().getAttribute("SearchAuthority");
    	        String  account_number=(String) request.getSession().getAttribute("User");
    	        map.put("Authority",sl);
    	        map.put("account_number",account_number);
                map.put("templateName", request.getParameter("templateName"));
                map.put("factoryName", request.getParameter("factoryName"));
                map.put("fileNumber", request.getParameter("fileNumber"));
                map.put("userName", request.getParameter("userName"));
                map.put("begindate", request.getParameter("begindate"));
                map.put("enddate", request.getParameter("enddate"));
                Integer count = this.TemplateSearchMapper.countBean(map);

                Integer size = DictionaryMethod.size;
                Pagenation page = new Pagenation(pageNum, size, count);
                map.put("start", page.getStartRow());
                map.put("size", page.getSize());
                list1 = this.TemplateSearchMapper.selectTaskListByPage(map);
                for(TNgcData TNgcData:list1){
               	 if(TNgcData.getAccountNumber()!=null){
               		  //通过账号查询到用户名,工厂名
               		 System.out.println("用户名"+TNgcData.getAccountNumber());
               		 String factoryID=UserBeanMapper.selectUserByAccountNum(TNgcData.getAccountNumber()).getFactory();
               		 System.out.println("工厂名"+factoryID);
               		 if(!factoryID.equals("")){
               			 TNgcData.setFactoryName(FactoryBeanMapper.findFactoryNameByFactoryNo(Integer.parseInt(factoryID)));
               		 }
               		 TNgcData.setUserName(UserBeanMapper.selectUserByAccountNum(TNgcData.getAccountNumber()).getUsername());
               		 TNgcData.setSimpleTime(TemplateBean.DateChange(TNgcData.getCreateTime()));
               		 
               	 }
               	 
                }
                page.setList(list1);
                returnMap.put("page", page);
                resultCode = "0";
           	
    		
		} catch (Exception e)
        {
            e.printStackTrace();
            msg = "按条件查询角色异常";
        }
        finally
        {
            returnMap.put("resultCode", resultCode);
            returnMap.put("msg", msg);
        }
        
        return returnMap;
		
	}

	@Override
	public void dispalyDetail(HttpServletRequest request, Model model) {
		String pageNum = request.getParameter("pageNum");
		String id=request.getParameter("id");//data表id
		Map<String, String> map;
		Map<String, String> map1=new HashMap<String, String>();

        TNgcData data=new TNgcData();
        List<ShowDateBean> showList=new ArrayList<ShowDateBean>();
        try
        {   data=TemplateSearchMapper.selectByPrimaryKey(Integer.parseInt(id));
            map=MapUtil.sortMapByKey(TbMapMapper.findAll());//按key值排序
            for (Map.Entry<String, String> entry : map.entrySet()) {
            	map1.put("id", id);
                map1.put("fieldName",entry.getKey());
             //   System.out.println(entry.getKey() + " " + TemplateSearchMapper.selectColumnByA(map1));
                
                if(TemplateSearchMapper.selectColumnByA(map1)==null||TemplateSearchMapper.selectColumnByA(map1).equals("")){
                	
                }else{
                ShowDateBean bean=new ShowDateBean();
                bean.setFieldName(entry.getValue());
                bean.setColumns(entry.getKey());
                bean.setFieldVal(TemplateSearchMapper.selectColumnByA(map1));

                if(TbDataMark.findDescription(map1)!=null){
                	
                	bean.setDescription(TbDataMark.findDescription(map1));
                    bean.setFlag(TbDataMark.findflag(map1));
                }else{
                	bean.setDescription("");
                    bean.setFlag(null);
                }
                showList.add(bean);
                }
            }
        	
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            model.addAttribute("showList", showList);
            model.addAttribute("data", data);
            model.addAttribute("pageNum", pageNum);
        }
	}
   //用户编辑确认识别结果
	@Override
	public Map<String, Object> executeEditDetail(HttpServletRequest request) {
		String msg = "";// 错误信息
        String resultCode = "1";//1失败,0成功
        String id = request.getParameter("id");//数据表id
        String list = request.getParameter("list");//字符串拼接的字段key和value的多字符串；例如：a1,1;a2,2;
        String[] Arry=list.split(";");
        Map<String, Object> returnMap = new HashMap<String, Object>();
    	Map<String, Object> map = new HashMap<String, Object>();
        try{
           for(String str:Arry){
        	String[] Brry=str.split(",");//a1,1  {fieldName}=#{result}
        	map.put("fieldName",Brry[0]);
        	map.put("result",Brry[1]);
        	map.put("id",id);
        	TemplateSearchMapper.updateDataByID(map);
        	map.put("flag1",SystemConstants.Data_SUCCESS);
        	TemplateSearchMapper.updateflag1(map);
        	resultCode="0";
           }
        }catch (Exception e)
        {
          e.printStackTrace();
          msg="识别结果确认更新有问题！！！";
        }
        returnMap.put("resultCode",resultCode);
        returnMap.put("msg",msg);
		return  returnMap;
	}

	@Override
	public Map<String, Object> executeupdateStatus(HttpServletRequest request) {
		String msg = "";// 错误信息
        String resultCode = "1";//1失败,0成功
        String ids = request.getParameter("ids");//数据表ids
        Map<String,Object> map=new HashMap<String,Object>();
        Map<String,Object> returnMap=new HashMap<String,Object>();
        String[] Array=ids.split(",");
		try{
			for(String str:Array){
				if(!str.equals("")){
					map.put("fieldName", SystemConstants.Data_flag1);
					map.put("result", SystemConstants.Data_flag1_Success);
					map.put("id", str);
					TemplateSearchMapper.updateDataByID(map);
				}
			}
			resultCode = "0";
		}catch(Exception e){
		  e.printStackTrace();
		}finally{
			 returnMap.put("resultCode",resultCode);
		     returnMap.put("msg",msg);
		}
		return  returnMap;
	}

	


}
