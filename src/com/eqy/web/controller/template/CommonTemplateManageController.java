package com.eqy.web.controller.template;

import com.eqy.Tess4j.Tess4j;
import com.eqy.constants.SystemConstants;
import com.eqy.tools.MapUtil;
import com.eqy.utils.FileUtil;
import com.eqy.utils.MultiPageSplit;
import com.eqy.utils.OperateImg;
import com.eqy.utils.newOperateImg;
import com.eqy.web.dao.TbTraineddataMapper;
import com.eqy.web.pojo.TbTraineddata;
import com.eqy.web.service.common_template.ICommonTemplateService;
import com.eqy.web.service.common_template.impl.CommonTemplateService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


/**
 * Created by chengkang
 * 2018/7/19 下午3:45
 */
@Controller
@RequestMapping(value = "/CommonTemplateManage")
public class CommonTemplateManageController {
    @Value( "${FILE_PATH}" )
    private String FILE_PATH;
    @Autowired
    private TbTraineddataMapper tbTraineddataMapper;
    @Autowired
    private ICommonTemplateService iCommonTemplateService;

    @RequestMapping(value = "/showCommonTemplateManage", method = RequestMethod.GET)
    public String showTemplateList(HttpServletRequest request, Model model){
    	// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	iCommonTemplateService.showTemplateListManage(request,model);
        	
        	
			
		} catch (Exception e) {
			e.printStackTrace();
		}

        return "commonTemplate_manage/commonManage";
    }
    
    @RequestMapping(value = "/editTemplate")
    @ResponseBody
    public Map<String, Object> EditTemplate(HttpServletRequest request,Model model)
    {
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		returnMap = iCommonTemplateService.executeEditTemplate(request,model);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return returnMap;
    }
    @RequestMapping(value = "/showEditTemplate", method = RequestMethod.GET)
    public String showEditTemplate(HttpServletRequest request, Model model){
    	// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	iCommonTemplateService.displayEditTemplate(request,model);
        	
        	
			
		} catch (Exception e) {
			e.printStackTrace();
		}

        return "commonTemplate_manage/edit_common";
    }
    
    /**
     * @Description 根据条件查询
     * @param request
     * @return TemplateBean对象
     */
    @RequestMapping(value = "/queryTemplateByCondition")
    @ResponseBody
    public Map<String, Object> queryUserByCondition(HttpServletRequest request)
    {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
            returnMap = iCommonTemplateService.executeSelectTemplateByCondition(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMap;
    }
    /**
     * <p>删除模板信息</p>
     * @param request
     * @return
     */
    @RequestMapping(value = "/delTemplate")
    @ResponseBody
    public Map<String, Object> removeUser(HttpServletRequest request)
    {
    	Map<String, Object> returnMap = new HashMap<>();
    	try {
    		returnMap = iCommonTemplateService.removeTemplate(request);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return returnMap;
    }
    @RequestMapping(value = "/uploadCommonTemplate", method = RequestMethod.GET)
    public String upload(){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        return "commonTemplate_manage/uploadImg";
    }

    @RequestMapping(value = "/Create", method = RequestMethod.POST)
    public @ResponseBody ModelAndView uploadCommonImg(@RequestParam("file") MultipartFile file, ModelAndView model, HttpServletRequest request) {
        //模板存到服务器
        String fileName = file.getOriginalFilename();
        String srcPath = FILE_PATH + "CommonTemplate/";
        int width = 0, height = 0;
        String str="";
        try {
            FileUtil.uploadFile(file.getBytes(), srcPath, fileName);//CommonTemplate下，为了查看模板详情
            File f = new File(srcPath+fileName);
            BufferedImage b = ImageIO.read(f);
            width = b.getWidth();
            height = b.getHeight();
            if(newOperateImg.readType(f).equals("tif")){
                //对模板分页,jpg存到CommonTemplate,返回jpg地址
                List<String> fileList = MultiPageSplit.tiffToJPEG(srcPath + fileName, FILE_PATH+"CommonTemplate/");
                str = "../uploads/CommonTemplate/" + fileList.get(0).substring(fileList.get(0).lastIndexOf("/") + 1);

            }else {
                str = "../uploads/CommonTemplate/"+fileName; //图片服务器src
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        List<TbTraineddata> trainList = tbTraineddataMapper.selectAllBean();
        List<String> fieldNameList = iCommonTemplateService.getFieldName();
        model.addObject("nameList", fieldNameList);
        model.addObject("trainList", trainList);
        model.addObject("fileName", fileName);
        model.addObject("jpgSrc", str);
        model.addObject("width", width);
        model.addObject("height", height);
        model.addObject("msg", "");
        model.setViewName("commonTemplate_manage/commonCreate");
        return model;
    }

    /**
     * <p>编辑查看模板</p>
     */
    @RequestMapping(value="/recreateTemplate")
    public @ResponseBody ModelAndView recreateTemplate(ModelAndView model, @RequestParam("fileName") String fileName, @RequestParam("templateName") String templateName){
        int width = 0, height = 0;
        String templateName1 = null;
        String URL = FILE_PATH+"CommonTemplate/"+fileName;
        String str = "";
        try {
            File file = new File(URL);
            if(newOperateImg.readType(file).equals("tif")){
                //对模板分页,jpg存到CommonTemplate,返回jpg地址
                List<String> fileList = MultiPageSplit.tiffToJPEG(URL, FILE_PATH+"CommonTemplate/");
                str = "../uploads/CommonTemplate/" + fileList.get(0).substring(fileList.get(0).lastIndexOf("/") + 1);
            }
            else{
                str = "../uploads/CommonTemplate/"+fileName; //图片服务器src
            }
            BufferedImage b = ImageIO.read(file);
            width = b.getWidth();
            height = b.getHeight();
            templateName1 = new String(templateName.getBytes("ISO-8859-1"),"UTF-8");
        }catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        List<String> fieldNameList = iCommonTemplateService.getFieldName();
        List<TbTraineddata> trainList = tbTraineddataMapper.selectAllBean();

        model.addObject("trainList", trainList);
        model.addObject("nameList", fieldNameList);
        model.addObject("jpgSrc", str);
        model.addObject("width", width);
        model.addObject("height", height);
        model.addObject("msg", "进入编辑模板状态,模板名为:"+templateName1);
        model.setViewName("commonTemplate_manage/commonCreate");
        return model;
    }

    /**
     * <p>实时截取识别 {文件名, 坐标}</p>
     * @param request
     * @return
     */
    @RequestMapping(value = "/tempOCR")
    public @ResponseBody String recognize(HttpServletRequest request){
        String fileSrc = request.getParameter("src");
        String fileName = fileSrc.substring(fileSrc.lastIndexOf("/")+1);//xx.jpg
        String filePath = FILE_PATH+"CommonTemplate/"+fileName;//jpg路径
        String result = "error";
        String traineddata = request.getParameter("trainName");
        int x1 = Integer.parseInt(request.getParameter("x1"));
        int y1 = Integer.parseInt(request.getParameter("y1"));
        int x2 = Integer.parseInt(request.getParameter("x2"));
        int y2 = Integer.parseInt(request.getParameter("y2"));
        int x = x1 < x2 ? x1 : x2;
        int y = y1 < y2 ? y1 : y2;
        int width = Math.abs(x2-x1);
        int height = Math.abs(y2-y1);
        try {
            result = Tess4j.covert(newOperateImg.GetBufferedImg(x,y,width,height,filePath),traineddata);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * <p>字段坐标存储</p>
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/saveCommonCoordinate")
    public @ResponseBody String updateCoordinate(HttpServletRequest request,HttpSession session){
        iCommonTemplateService.TemplateAddManage(request,session);
        return SystemConstants.WEB_SUCCESS;
    }
    
    
    @RequestMapping(value = "/findCoordinateAndnote")
    public @ResponseBody String findCoordinateAndnote(HttpServletRequest request){
        String fieldName = request.getParameter("fieldName");
        String templateName = null;
        String str="";
        try {
            templateName = new String(request.getParameter("templateName").trim().getBytes("iso8859-1"), "UTF-8");
            str = iCommonTemplateService.getCommonCoordinateAndnote(templateName,fieldName);//获取坐标
            
        } catch(Exception e) {
            e.printStackTrace();
        }
       
        return str ;
    }
    
    /**
     * tiff模板名称判断接口
     * @param request
     * @return
     */
    @RequestMapping(value="/CountBean", method = RequestMethod.POST)
    public @ResponseBody  Map<String,Object> CountBean(HttpServletRequest request)  {
        Map<String,Object> resultMap;
        resultMap =iCommonTemplateService.templateCountBean(request);
        return resultMap;
    }
}
