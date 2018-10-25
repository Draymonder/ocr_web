package com.eqy.web.controller.task;

import com.eqy.constants.SystemConstants;
import com.eqy.utils.*;
import com.eqy.web.service.task.ITaskService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "/recognizeManage")
public class OperateController {

    private static Logger log = LoggerFactory.getLogger(OperateController.class);

	@Value( "${FILE_PATH}" )
	private String FILE_PATH;

	@Autowired
	private ITaskService iTaskService;

	@RequestMapping(value = "/recognitionTask", method = RequestMethod.GET)
	public String gotoTask(HttpServletRequest request, Model model){
		// 验证用户是否登录，未登录返回登录页面
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated())
        {
            return "redirect:/";
        }
        try {
        	iTaskService.showTaskListManage(request,model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "recognition_manage/recognitionBuild";
	}

	/**
	 * <p>处理上传任务</>
	 * @param files
	 * @param session
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */

	@RequestMapping(value = "/newTask", method = RequestMethod.POST)
    public @ResponseBody String multipleTaskOperation(@RequestParam("files") MultipartFile[] files, HttpSession session) throws Exception {
        String result = SystemConstants.WEB_SUCCESS;//0
        String  account =(String) session.getAttribute("User");
		String file_path = FILE_PATH+"tiff/";
        if(files.length!=0){
            new Thread(new MultipartFileHelper(files, file_path, account)).start();
        }else result = SystemConstants.WEB_FAILURE;
        return result;

    }


//	@RequestMapping(value = "/newTask1", method = RequestMethod.POST)
//	public @ResponseBody String multipleTaskOperatio1n(@RequestParam("files") MultipartFile[] files, HttpSession session) throws IllegalStateException, IOException  {
//		String result = SystemConstants.WEB_SUCCESS;//0
//        String flag = SystemConstants.TASK_UNFINISHED;
//        final String  account =(String) session.getAttribute("User");
//		final String file_path = FILE_PATH+"tiff/";
//        ExecutorService pool = Executors.newCachedThreadPool();
//
//		if(files.length != 0){
//			final int task_id = iTaskService.InsertTask(account);//插入任务记录
//			for(final MultipartFile f: files) {
//				if (!f.isEmpty()) {
//                    //创建线程,负责识别任务的执行
//                    long s = System.currentTimeMillis();
//                    pool.execute(new Runnable() {
//                        @Override
//                        public void run() {
//                            final String path = file_path + f.getOriginalFilename();
//                            try {
//                                FileUtil.uploadFile(f.getBytes(), file_path, f.getOriginalFilename());//tiff上传至服务器
//                                iTaskService.taskManage(path, account, task_id);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//                    long e = System.currentTimeMillis();
//                    log.warn("这次提交耗时---"+(e-s)/1000+"s");
//                } else {
//					result = SystemConstants.WEB_FAILURE;
//				}
//			}
//			//创建线程用来监听该pool
//			new Thread(new ThreadPoolMonitor2(pool, flag, task_id)).start();
//		}
//		return result;
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
			returnMap=iTaskService.executeQueryTaskListByCondition(request);
		}catch(Exception e){
			e.printStackTrace();
		}
	   return  returnMap;
	 }
}
