package com.eqy.web.service.task.imp;

import com.eqy.Tess4j.Tess4j;
import com.eqy.constants.SystemConstants;
import com.eqy.tools.CoordinateCovert;
import com.eqy.utils.*;
import com.eqy.web.dao.TNgcFactoryMapper;
import com.eqy.web.dao.TRuleMapperMapper;
import com.eqy.web.dao.TbDataMarkMapper;
import com.eqy.web.dao.TbTaskMapper;
import com.eqy.web.dao.TemplateRecognitionMapper;
import com.eqy.web.dao.TemplateSearchMapper;
import com.eqy.web.dao.UserBeanMapper;
import com.eqy.web.pojo.Pagenation;
import com.eqy.web.pojo.TbTask;
import com.eqy.web.service.mail.IMailService;
import com.eqy.web.service.task.ITaskService;
import com.eqy.web.service.template.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chengkang
 * 2018/6/21 下午7:56
 */
@Service(value = "taskService")
//@Transactional
public class TaskServiceImpl implements ITaskService {
    @Autowired
    IMailService iMailService;
    @Autowired
    TemplateRecognitionMapper templateRecognitionMapper;
    @Autowired
    TRuleMapperMapper tRuleMapperMapper;
    @Autowired
    TemplateService templateService;
    @Autowired
    TemplateSearchMapper templateSearchMapper;
    @Autowired
    TbDataMarkMapper tbDataMarkMapper;
    @Autowired
    TbTaskMapper tbTaskMapper;
    @Autowired
    TNgcFactoryMapper tNgcFactoryMapper;
    @Autowired
    UserBeanMapper userBeanMapper;
    @Value( "${FILE_PATH}" )
    private String FILE_PATH;


    /**
     *<p>核心任务执行模块</p>
     * @param tiff_path tiff图片路径
     * @param account 创建人账号信息
     * @param task_id 任务id
     */
    @Override
    public void taskManage(String tiff_path, String account, int task_id) throws IOException {
        //tiff分页
        List<String> jpg_pathList = MultiPageSplit.tiffToJPEG(tiff_path, FILE_PATH+"temp/");
        int size = jpg_pathList.size();
        int j = 2;//处理两页（3页，4页取后两页）
        if(size<2) j = 1;//处理一页情况
        String fileName = tiff_path.substring(tiff_path.lastIndexOf("/")+1, tiff_path.lastIndexOf("."));//文件名
        String fileNumber = tiff_path.substring(tiff_path.lastIndexOf("/")+1);//全名，用于下载，兼容tif TIF
        Map<String, Object> resultMap = templateService.getTemplateNameANDResult(jpg_pathList.get(size-j));
         String templateName = (String)resultMap.get("templateName");
        if(!templateName.equals("")){
            //线程池
            //ExecutorService pool = Executors.newFixedThreadPool(30);
            StringBuilder sub_src = new StringBuilder(FILE_PATH);
            sub_src.append("temp/");
            sub_src.append(fileName);
            sub_src.append("/");
            File sub_file = new File(sub_src.toString());
            if(!sub_file.exists()) sub_file.mkdirs();
            //insert数据前判断是否存在同名图片数据
            if(templateSearchMapper.countFile(fileNumber)==0){
                //update前insert基本信息,data库
                Map<String, Object> insertMap = new HashMap<>();
                insertMap.put("templateName", templateName);
                insertMap.put("createTime", DateTransfer.DateChange(new Date()));
                insertMap.put("fileNumber", fileNumber);
                insertMap.put("accountNumber", account);
                insertMap.put("taskId", task_id);
                templateSearchMapper.insertBasicInfo(insertMap);
            }else{
                Map<String, Object> updateMap = new HashMap<>();
                updateMap.put("createTime", DateTransfer.DateChange(new Date()));//更新时间
                updateMap.put("fileNumber", fileNumber);
                updateMap.put("taskId", task_id);
                updateMap.put("accountNumber", account);
                updateMap.put("templateName", templateName);
                updateMap.put("flag2", SystemConstants.flag2_Data_changed);
                templateSearchMapper.updateCreateTime(updateMap);
            }
            int id = templateSearchMapper.selectIDbyFileNumber(fileNumber);//data_id
            if(tbDataMarkMapper.countDataMark(id)>0)
                tbDataMarkMapper.deleteDataMark(id);//已存在则删除所有相关mark记录
            //数字化任务处理
            int[] Array;//具体坐标

            Map<String, Object> CoordinateMap;//所有坐标的Map
            for(int i = 0; i < j; i++){
                //根据模板名和页码查询所有坐标集合{字段名：坐标}
                CoordinateMap = templateService.FindCoordinate(templateName, i+1);
                //如果size>=2处理第二页左还是右
                if(i == 1 && !Tess4j.covert(newOperateImg.GetBufferedImg(971,127,83,79,
                                            jpg_pathList.get(size-j+1)),"num").equals("G"))
                    CoordinateMap = templateService.FindCoordinate("left",1);
                if(CoordinateMap != null) {
                    for(Map.Entry me : CoordinateMap.entrySet()) {
                        Array = CoordinateCovert.transform(me.getValue().toString());
                        Map<String, Object> inputMap = new HashMap<>();
                        inputMap.put("id", id);//数据id
                        inputMap.put("fieldName", me.getKey());
                        //多线程识别一页所有字段
//                        pool.execute(new TaskManager(tbDataMarkMapper, tRuleMapperMapper, templateSearchMapper,
//                                Array[0], Array[1], Array[2], Array[3], jpg_pathList.get(size-j+i), sub_src.toString() + me.getKey() + ".jpg", inputMap));
                        new TaskManager(tbDataMarkMapper, tRuleMapperMapper, templateSearchMapper,
                                Array[0], Array[1], Array[2], Array[3], jpg_pathList.get(size-j+i),
                                sub_src.toString() + me.getKey() + ".jpg", inputMap).run();
                    }
                }
            }
//            pool.shutdown();//单文件识别提交完后关闭该线程池，未完成任务继续
//            while (!pool.isTerminated()) {//等待任务全部完成
//            }
            //完成后，更新规则过滤情况
            //UpdateTaskStatus(task_id);
            if(tbDataMarkMapper.countByDataId(id) == 0){
                Map<String, Object> mapB = new HashMap<>();
                mapB.put( "id", id);
                mapB.put("flag2", SystemConstants.flag2_Data_unchanged);
                templateSearchMapper.updateflag2(mapB);
            }
        }
        else {
            iMailService.sendemail(fileNumber,account);
        }
    }

    @Override
    public void showTaskListManage(HttpServletRequest request, Model model) {
        try
        {

            Map<String, Object> map = new HashMap<>();
            Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
            Integer size = DictionaryMethod.size;
            int sl=(int) request.getSession().getAttribute("SearchAuthority");
            String  account_number=(String) request.getSession().getAttribute("User");
            map.put("Authority",sl);//0是普通权限，1是超级权限看所有的
            map.put("account_number",account_number);
            Integer count = this.tbTaskMapper.countTaskBean(map);
            Pagenation page = new Pagenation(pageNum, size, count);
            map.put("size", page.getSize());//每页行数
            map.put("start", page.getStartRow());//重第一行开始,系统size为10
            List<TbTask> list = this.tbTaskMapper.selectTaskListByPage(map);
            for(TbTask task:list){
                if(task.getCreateTime()!=null){
                    task.setSimpleTime(DateTransfer.DateChange(task.getCreateTime()));
                }
                if(task.getFinishTime()!=null) task.setStringFinishTime(DateTransfer.DateChange(task.getFinishTime()));
                if(task.getFactoryId()!=0){
                    task.setFactory_name(tNgcFactoryMapper.selectByPrimaryKey(task.getFactoryId()).getName());
                }

            }
            page.setList(list);
            model.addAttribute("page", page);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public Map<String, Object> executeQueryTaskListByCondition(HttpServletRequest request) {
        String msg = "";// 错误信息
        String resultCode = "1";
        Map<String, Object> map = new HashMap<>();
        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
        Integer size = DictionaryMethod.size;
        int sl=(int) request.getSession().getAttribute("SearchAuthority");
        String  account_number=(String) request.getSession().getAttribute("User");
        map.put("Authority",sl);//0是普通权限，1是超级权限看所有的
        map.put("account_number",account_number);
        map.put("factoryName", request.getParameter("condition"));
        map.put("begindate", request.getParameter("begindate"));
        map.put("enddate", request.getParameter("enddate"));
        Integer count =tbTaskMapper.countTaskBean(map);
        Pagenation page = new Pagenation(pageNum, size, count);
        map.put("start", page.getStartRow());
        map.put("size", page.getSize());

        Map<String, Object> returnMap = new HashMap<String, Object>();
        try{
            List<TbTask> list = this.tbTaskMapper.selectTaskListByPage(map);
            resultCode="0";
            for(TbTask task:list){
                if(task.getFinishTime()!=null) task.setStringFinishTime(DateTransfer.DateChange(task.getFinishTime()));
                if(task.getCreateTime()!=null){
                    task.setSimpleTime(DateTransfer.DateChange(task.getCreateTime()));
                }
                if(task.getFactoryId()!=0){
                    task.setFactory_name(tNgcFactoryMapper.selectByPrimaryKey(task.getFactoryId()).getName());
                }
            }
            page.setList(list);
            returnMap.put("page", page);
        } catch (Exception e)
        {
            e.printStackTrace();
            msg="按条件查询任务有误";
        }finally{
            returnMap.put("resultCode", resultCode);
            returnMap.put("msg", msg);
        }
        return returnMap;
    }

    @Override
    public int InsertTask() {

        TbTask task=new TbTask();
        task.setCreator(SystemConstants.FTP_TASK_CREATOR);
        task.setCreateTime(new Date());
        task.setFactoryId(SystemConstants.FTP_TASK_FACTORY_ID);
        tbTaskMapper.InsertTask(task);
        return task.getId();
    }

    @Override
    public int InsertTask(String account) {

        TbTask task=new TbTask();
        task.setCreator(account);
        task.setCreateTime(new Date());
        task.setFactoryId(Integer.parseInt(userBeanMapper.selectUserByAccountNum(account).getFactory()));
        tbTaskMapper.InsertTask(task);
        return task.getId();
    }

    @Override
    public void UpdateTaskStatus(int task_id) {
        //更新任务状态
        String flag = SystemConstants.TASK_FINISHED;
        Map<String, Object> mapA = new HashMap<>();
        mapA.put("id", task_id);
        mapA.put("status", flag);
        mapA.put("finishTime", new Date());
        tbTaskMapper.UpdateTaskStatusAndTime(mapA);
    }



}
