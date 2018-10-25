package com.eqy.web.controller.template;

import com.eqy.Tess4j.Tess4j;
import com.eqy.constants.SystemConstants;
import com.eqy.tools.MapUtil;
import com.eqy.utils.FileUtil;
import com.eqy.utils.MultiPageSplit;
import com.eqy.utils.OperateImg;
import com.eqy.web.service.template.TemplateService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.ws.commons.schema.constants.Constants;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by chengkang
 * 2018/5/23 下午4:00
 */
@Controller
@RequestMapping(value = "/templateManage")
public class TemplateManageController {

    @Value( "${FILE_PATH}" )
    private String FILE_PATH;

    @Autowired
    private TemplateService templateService;

    @RequestMapping(value = "/showTemplateManage")
    public String showTemplateList(HttpServletRequest request, Model model){
        // 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
            templateService.templateManage(request, model);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "template_manage/templateManage";


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
            returnMap = templateService.executeSelectTemplateByCondition(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMap;
    }

    /**
     * @Description 展示编辑页面
     * @param request
     * @return TemplateBean对象
     */
    @RequestMapping(value ="/showEditTemplate")
    public String  showEditTemplate(HttpServletRequest request,Model model)
    {
    	 Subject subject = SecurityUtils.getSubject();
         if (!subject.isAuthenticated())
         {
             return "redirect:/";
         }
        try {
            templateService.displayEditTemplate(request,model);
        } catch (Exception e) {
            e.printStackTrace();
        }       
        return "template_manage/edit_template";
    }
    
    /**
     * 编辑模板相关信息
     * @param request
     * @return TemplateBean对象
     */
    @RequestMapping(value = "/editTemplate")
    @ResponseBody
    public Map<String, Object> EditTemplate(HttpServletRequest request,Model model)
    {
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	try {
    		returnMap = templateService.executeEditTemplate(request,model);
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
    		returnMap = templateService.removeTemplate(request);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return returnMap;
    }

    @RequestMapping(value = "/findCoordinate")
    public @ResponseBody String findCoordinate(HttpServletRequest request){
        String fieldName = request.getParameter("fieldName");
        String templateName = null;
        try {
            templateName = new String(request.getParameter("templateName").trim().getBytes("iso8859-1"), "UTF-8");
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String str = templateService.getCoordinate(templateName,fieldName);
        return str ;
    }

    /**
     * <p>编辑查看模板</p>
     */
    @RequestMapping(value="/recreateTemplate")
    public @ResponseBody ModelAndView recreateTemplate(ModelAndView model, @RequestParam("fileName") String fileName){
        String srcPath = FILE_PATH+"template/";
        //对模板分页,jog存到temp,返回jpg地址
        List<String> fileList = MultiPageSplit.tiffToJPEG(srcPath + fileName, FILE_PATH+"temp/");
        List<String> jpgList = new ArrayList<>();
        for(String jpgStr : fileList){
            String s = "../uploads/temp/" + jpgStr.substring(jpgStr.lastIndexOf("/") + 1);
            jpgList.add(s);
        }
        Map<String, Object> resultMap = templateService.getTemplateNameANDResult(fileList.get(0));//查询模板识别标志
        Map<String, String> FieldMap = templateService.FindAllFieldName();//所有字段值
        model.addObject("nameMap",  MapUtil.sortMapByKey(FieldMap));
        model.addObject("jpgList", jpgList);
        model.addObject("result", resultMap.get("result"));
        model.setViewName("template_manage/templateCreate");
        model.addObject("msg", "进入编辑模板状态,模板名为:"+resultMap.get("templateName"));
        return model;
    }



    /**
     * <p>tiff模板上传</p>
     * @param file
     * @param model
     * @param request
     * @return model
     * @throws IOException
     */
    @RequestMapping(value="/createTemplate", method = RequestMethod.POST)
    public @ResponseBody ModelAndView uploadImg(@RequestParam("file") MultipartFile file, ModelAndView model, HttpServletRequest request){
        //模板存到服务器
        String fileName = file.getOriginalFilename();
        String srcPath = FILE_PATH+"template/";
        try {
            FileUtil.uploadFile(file.getBytes(), srcPath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //对模板分页,jpg存到temp,返回jpg地址
        List<String> fileList = MultiPageSplit.tiffToJPEG(srcPath + fileName, FILE_PATH+"temp/");
        List<String> jpgList = new ArrayList<>();
        for(String jpgStr : fileList){
            String s = "../uploads/temp/" + jpgStr.substring(jpgStr.lastIndexOf("/") + 1);
            jpgList.add(s);
        }

        //验证该模板是否需要制作
        Map<String, Object> resultMap = templateService.getTemplateNameANDResult(fileList.get(0));
        String msg;
        if(resultMap.get("templateName").equals("")){
            msg="";
        }else{
            msg="该模板模板库已拥有,模板名称为:"+resultMap.get("templateName");
        }
        Map<String, String> FieldMap = templateService.FindAllFieldName();//所有字段
        model.addObject("nameMap",  MapUtil.sortMapByKey(FieldMap));
        model.addObject("msg", msg);
        model.addObject("jpgList", jpgList);
        model.addObject("result", resultMap.get("result"));
        model.addObject("fileName", fileName);
        model.setViewName("template_manage/templateCreate");
        return model;
    }



    /**
     * <p>制作提交 {模板名, 14个结果字符串}</p>
     * @param request
     * @return
     */
    @RequestMapping(value="/SubmitTemplate", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> SubmitTemplate(HttpServletRequest request)  {
        return templateService.templateRecognitionMake(request);
    }


    /**
     * <p>实时截取识别 {文件名, 坐标}</p>
     * @param request
     * @return
     */
    @RequestMapping(value = "/tempRecognize")
    public @ResponseBody String recognize(HttpServletRequest request){
        String fileSrc = request.getParameter("src");
        String fileName = fileSrc.substring(fileSrc.lastIndexOf("/")+1);
        String filePath = FILE_PATH+"temp/"+fileName;//jpg路径
        String result = "异常";
        int x1 = Integer.parseInt(request.getParameter("x1"));
        int y1 = Integer.parseInt(request.getParameter("y1"));
        int x2 = Integer.parseInt(request.getParameter("x2"));
        int y2 = Integer.parseInt(request.getParameter("y2"));
        int x = x1 < x2 ? x1 : x2;
        int y = y1 < y2 ? y1 : y2;
        int width = Math.abs(x2-x1);
        int height = Math.abs(y2-y1);
        try {
            result = Tess4j.covert(new OperateImg(x, y, width, height, filePath).getBufferedImage(), "num");

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
    @RequestMapping(value = "/saveCoordinate")
    public @ResponseBody String axis_catch(HttpServletRequest request,HttpSession session){
        templateService.TemplateAddManage(request,session);
        return SystemConstants.WEB_SUCCESS;
    }

    @RequestMapping(value = "/uploadTemplate", method = RequestMethod.GET)
    public String gotoUpload(){
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        return "template_manage/uploadimg";
    }


    /**
     * tiff模板名称判断接口
     * @param request
     * @return
     */
    @RequestMapping(value="/CountBean", method = RequestMethod.POST)
    public @ResponseBody  Map<String,Object> CountBean(HttpServletRequest request)  {
        Map<String,Object> resultMap;
        resultMap = templateService.templateCountBean(request);
        return resultMap;
    }
}
