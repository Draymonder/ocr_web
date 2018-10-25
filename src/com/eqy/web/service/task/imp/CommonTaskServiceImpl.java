package com.eqy.web.service.task.imp;

import com.eqy.constants.SystemConstants;
import com.eqy.tools.CoordinateCovert;
import com.eqy.utils.*;
import com.eqy.web.dao.*;
import com.eqy.web.pojo.CommonTemplateBean;
import com.eqy.web.pojo.Pagenation;
import com.eqy.web.pojo.TbCommonTask;
import com.eqy.web.pojo.TbTraineddata;
import com.eqy.web.service.mail.IMailService;
import com.eqy.web.service.task.ICommonTaskService;
import com.eqy.web.service.template.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chengkang
 * 2018/6/21 下午7:56
 */
@Service(value = "CommonTaskService")
public class CommonTaskServiceImpl implements ICommonTaskService {
    @Autowired
    CommonTemplateBeanMapper commonTemplateBeanMapper;
    @Autowired
    IMailService iMailService;
    @Autowired
    TNgcDataCommonMapper tNgcDataCommonMapper;
    @Autowired
    TbCommonDataMarkMapper tbCommonDataMarkMapper;
    @Autowired
    TbCommonTaskMapper tbCommonTaskMapper ;
    @Autowired
    TNgcFactoryMapper tNgcFactoryMapper;
    @Autowired
    TCommonRuleMapperMapper tCommonRuleMapperMapper;
    @Autowired
    TbCommonRuleMapper tbCommonRuleMapper;
    @Autowired
    TbTraineddataMapper tbTraineddataMapper;
    @Autowired
    UserBeanMapper userBeanMapper;
    @Value( "${FILE_PATH}" )
    private String FILE_PATH;

    /**
     *<p>核心任务执行模块</p>
     * @param file_path tiff图片路径
     * @param account 创建人账号信息
     * @param task_id 任务id
     */
    @Override
    public void common_taskManage(String file_path, String account, int task_id, int templateID, String traineddata) {
        //线程池
        //ExecutorService pool = Executors.newFixedThreadPool(SystemConstants.MAX_THREAD_POOL_SIZE_FOR_Common);
        String flag = SystemConstants.TASK_UNFINISHED;
        String fileName = file_path.substring(file_path.lastIndexOf("/")+1, file_path.lastIndexOf("."));
        String fileNumber = file_path.substring(file_path.lastIndexOf("/")+1);
        StringBuilder sub_src = new StringBuilder(FILE_PATH);
        sub_src.append("temp/");
        sub_src.append(fileName);
        sub_src.append("/");

        File sub_file = new File(sub_src.toString());
        if(!sub_file.exists()) sub_file.mkdirs();
        String templateName = commonTemplateBeanMapper.findTemplateNameByID(templateID);
        //insert数据前判断是否存在同名图片数据
        if(tNgcDataCommonMapper.countFile(fileNumber)!=0){
            Map<String, Object> updateMap = new HashMap<>();
            updateMap.put("taskId", task_id);
            updateMap.put("createTime", DateTransfer.DateChange(new Date()));//更新时间
            updateMap.put("fileNumber", fileNumber);
            updateMap.put("accountNumber", account);
            updateMap.put("templateName", templateName);
            updateMap.put("flag2", SystemConstants.flag2_Data_changed);//更新为默认
            tNgcDataCommonMapper.updateCreateTime(updateMap);
        }else{
            //新文件，插入基本信息
            Map<String, Object> IMap = new HashMap<>();
            IMap.put("templateName", templateName);
            IMap.put("createTime", DateTransfer.DateChange(new Date()));
            IMap.put("fileNumber", fileNumber);
            IMap.put("accountNumber", account);
            IMap.put("taskId", task_id);
            tNgcDataCommonMapper.insertBasicInfo(IMap);
        }
        int id = tNgcDataCommonMapper.selectIDbyFileNumber(fileNumber);//data_id
        if(tbCommonDataMarkMapper.countDataMark(id)>0)
            tbCommonDataMarkMapper.deleteDataMark(id);//已存在则删除所有相关mark记录
        //数字化任务处理
        int[] Array;//具体坐标
        Map<String, Object> CoordinateMap;//所有坐标的Map
        //根据模板名和页码查询所有坐标集合{字段名：坐标}
        CoordinateMap = commonTemplateBeanMapper.findAllCoordinate(templateName);
        for (Map.Entry me : CoordinateMap.entrySet()) {
            Array = CoordinateCovert.transform(me.getValue().toString());
            Map<String,Object> inputMap = new HashMap<>();
            inputMap.put("id", id);//数据id
            inputMap.put("fieldName", me.getKey());
//            pool.execute(new CommonRunnableTask(Array[0], Array[1], Array[2], Array[3],
//                    file_path, sub_src.toString()+me.getKey()+".jpg",
//                    inputMap, templateID, traineddata,
//                    tCommonRuleMapperMapper, tbCommonDataMarkMapper,tNgcDataCommonMapper
//            ));
            String subFileFullSrc = sub_src.toString() + me.getKey();
            CommonRunnableTask commonTask = new CommonRunnableTask();
            commonTask.setFile_src(file_path);
            commonTask.setSub_src(subFileFullSrc);
            commonTask.setMap(inputMap);
            commonTask.setTraineddata(traineddata);
            commonTask.setX(Array[0]);
            commonTask.setY(Array[1]);
            commonTask.setWidth(Array[2]);
            commonTask.setHeight(Array[3]);
            commonTask.setTemplateID(templateID);
            commonTask.setTbCommonDataMarkMapper(tbCommonDataMarkMapper);
            commonTask.settCommonRuleMapperMapper(tCommonRuleMapperMapper);
            commonTask.settNgcDataCommonMapper(tNgcDataCommonMapper);
            commonTask.run();
        }
        //创建线程监听线程池状态
//        new Thread(new ThreadPoolMonitor1(tbCommonTaskMapper,tbCommonDataMarkMapper,
//                tNgcDataCommonMapper,
//          pool, flag, task_id, id)).start();
        //更新规则过滤情况
        if(tbCommonDataMarkMapper.countByDataId(id) == 0){
            Map<String, Object> ruleMap = new HashMap<>();
            ruleMap.put( "id", id);
            ruleMap.put("flag2", SystemConstants.flag2_Data_unchanged);
            tNgcDataCommonMapper.updateflag2(ruleMap);
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
            Integer count = this.tbCommonTaskMapper.countTaskBean(map);
            Pagenation page = new Pagenation(pageNum, size, count);
            map.put("size", page.getSize());//每页行数
            map.put("start", page.getStartRow());//重第一行开始,系统size为10
            List<TbCommonTask> list = this.tbCommonTaskMapper.selectTaskListByPage(map);
            List<CommonTemplateBean> list1=commonTemplateBeanMapper.selectAll();
            List<TbTraineddata> list2 =tbTraineddataMapper.selectAllBean();
            for(TbCommonTask task:list){
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
            model.addAttribute("list1", list1);
            model.addAttribute("list2", list2);
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
        Integer count =tbCommonTaskMapper.countTaskBean(map);
        Pagenation page = new Pagenation(pageNum, size, count);
        map.put("start", page.getStartRow());
        map.put("size", page.getSize());

        Map<String, Object> returnMap = new HashMap<String, Object>();
        try{
            List<TbCommonTask> list = this.tbCommonTaskMapper.selectTaskListByPage(map);
            resultCode="0";
            for(TbCommonTask task:list){
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

    /**
     * 插入一条任务，返回任务ID
     * @param account
     * @return
     */
    @Override
    public int InsertTask(String account) {

        TbCommonTask task=new TbCommonTask();
        task.setCreator(account);
        task.setCreateTime(new Date());
        task.setFactoryId(Integer.parseInt(userBeanMapper.selectUserByAccountNum(account).getFactory()));
        tbCommonTaskMapper.InsertTask(task);
        return task.getId();
    }

    /**
     * 任务完成更新完成时间和状态
     * @param task_id
     */
    @Override
    public void UpdateTaskStatus(int task_id) {
        Map<String, Object> mapA = new HashMap<>();
        mapA.put("id", task_id);
        mapA.put("status", SystemConstants.TASK_FINISHED);
        mapA.put("finishTime", new Date());
        tbCommonTaskMapper.UpdateTaskStatusAndTime(mapA);
    }
}
