package com.eqy.web.controller.task;

import com.eqy.constants.SystemConstants;
import com.eqy.utils.DateTimeUtil;
import com.eqy.utils.FileUtil;
import com.eqy.utils.MultipartFileHelper;
import com.eqy.utils.MultipartFileHelper1;
import com.eqy.web.service.task.ICommonTaskService;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

@Controller
@RequestMapping(value = "/CommonRecognizeManage")
public class CommonOperateController {

	@Value( "${FILE_PATH}" )
	private String FILE_PATH;

	@Autowired
	private ICommonTaskService iCommonTaskService;

	@RequestMapping(value = "/recognitionTask", method = RequestMethod.GET)
	public String gotoTask(HttpServletRequest request, Model model){
		// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	iCommonTaskService.showTaskListManage(request,model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "commonRecognition_manage/recognitionBuild";
	}
    @RequestMapping(value = "/newTask", method = RequestMethod.POST)
    public @ResponseBody String CommonTaskOperation(@RequestParam("files") MultipartFile[] files, HttpSession session, HttpServletRequest request) throws IllegalStateException, IOException {
        String result = SystemConstants.WEB_SUCCESS;//0
        int templateID = Integer.parseInt(request.getParameter("templateID"));
        String traineddata = request.getParameter("traineddata");
        String account = (String) session.getAttribute("User");
        String file_path = FILE_PATH + "common/";
        if(files.length != 0) {
            MultipartFileHelper1 m = new MultipartFileHelper1();
            m.setAccount(account);
            m.setFile_path(file_path);//路径
            m.setFiles(files);//待识别文件
            m.setTemplateID(templateID);//模板
            m.setTraineddata(traineddata);//训练集
            new Thread(m).start();//开启新线程处理，避免页面停留
        }else result = SystemConstants.WEB_FAILURE;
        return result;
    }
//	@RequestMapping(value = "/newTask", method = RequestMethod.POST)
//	public @ResponseBody String CommonTaskOperation(@RequestParam("files") MultipartFile[] files, HttpSession session, HttpServletRequest request) throws IllegalStateException, IOException {
//		String result = SystemConstants.WEB_SUCCESS;//0
//		final int templateID = Integer.parseInt(request.getParameter("templateID"));
//		final String traineddata = request.getParameter("traineddata");
//		final String account = (String) session.getAttribute("User");
//        ExecutorService pool = Executors.newFixedThreadPool(SystemConstants.MAX_THREAD_POOL_SIZE_FOR_UPLOAD);
//        final String file_path = FILE_PATH + "common/";
//		if(files.length != 0) {
//			final int task_id = iCommonTaskService.InsertTask(account);//插入任务记录
//			for(final MultipartFile file : files) {
//				if(!file.isEmpty()) {
//                    pool.execute(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                FileUtil.uploadFile(file.getBytes(), file_path, file.getOriginalFilename());//图片上传至服务器
//                            } catch(Exception e) {
//                                e.printStackTrace();
//                            }
//                            final String path = file_path + file.getOriginalFilename();
//                            iCommonTaskService.common_taskManage(path, account, task_id, templateID, traineddata);
//                        }
//                    });
//				}
//			}
//			pool.shutdown();
//		}
//        return result;
//	}

	/**
	 * <p>按条件查询</p>
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryTaskListByCondition", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryTaskListByCondition( HttpServletRequest request)   {
		Map<String,Object> returnMap=new HashMap<>();
		try{
			returnMap=iCommonTaskService.executeQueryTaskListByCondition(request);
		}catch(Exception e){
			e.printStackTrace();
		}
	   return  returnMap;
	 }
}
