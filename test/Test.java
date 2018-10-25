import com.eqy.Tess4j.Tess4j;
import com.eqy.constants.SystemConstants;
import com.eqy.tools.CoordinateCovert;
import com.eqy.tools.CovertUtil;
import com.eqy.utils.*;
import com.eqy.web.dao.*;
import com.eqy.web.pojo.Pagenation;
import com.eqy.web.pojo.TbDataMark;
import com.eqy.web.pojo.TbTask;
import com.eqy.web.pojo.TbTraineddata;
import com.eqy.web.service.template.TemplateService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Created by chengkang
 * 2018/6/7 下午3:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/applicationContext.xml"})
public class Test {


    @Autowired
    TbTaskMapper tbTaskMapper;
    @Autowired
    TbMapMapper tbMapMapper;
    @Autowired
    TbDataMarkMapper tbDataMarkMapper;
    @Autowired
    TRuleMapperMapper tRuleMapperMapper;
    @Autowired
    TemplateBeanMapper templateBeanMapper;
    @Autowired
    TemplateService templateService;
    @Autowired
    CommonTemplateBeanMapper commonTemplateBeanMapper;
    @Autowired
    TbCommonMapMapper tbCommonMapMapper;
    @Autowired
    TCommonRuleMapperMapper tCommonRuleMapperMapper;

    @Autowired
    TNgcDataCommonMapper tNgcDataCommonMapper;
    @Autowired
    TemplateSearchMapper templateSearchMapper;
    @Autowired
    TemplateRecognitionMapper templateRecognitionMapper;
    @Autowired
    TbTraineddataMapper tbTraineddataMapper;
    @org.junit.Test
    public void test(){
        Map<String, Object> CoordinateMap;
        CoordinateMap = templateService.FindCoordinate("64 齿形-Cα-fFu-fKo 齿向-Cβ-fu-fo", 2);
        for(int i=48;i<49; i++){
            for(int j = 186; j<222; j++){
                Map<String, Object> updateMap1 = new HashMap<>();
                updateMap1.put("templateName", "t"+i);
                updateMap1.put("pageNo", 2);
                updateMap1.put("coordinate", CoordinateMap.get("a"+j));
                updateMap1.put("fieldName", "a"+j);
                templateBeanMapper.updateCoordinate(updateMap1);
            }
        }
    }
    @org.junit.Test
    public void multipleCreateTemplate() {
        String jpgURL = "/Users/chengkang/Desktop/jpg/";
        String[] coordinate = SystemConstants.TEMPLATE_RECOGNITON_COORDINATE;
        Map<String, Object> resultMap;
        Map<String, Object> CoordinateMap;
        CoordinateMap = templateService.FindCoordinate("64 齿形-Cα-fFu-fKo 齿向-Cβ-fu-fo", 1);
        for(int i=48; i<49; i++){
            String tifURL = "/Users/chengkang/Desktop/tif/"+i+".TIF";
            String fileName = tifURL.substring(tifURL.lastIndexOf("/")+1);
            Map<String, Object> insertMap = new HashMap<>();
            insertMap.put("templateName", "t"+i);
            insertMap.put("pageNo", 1);
            insertMap.put("remark", "自动生成模板");
            insertMap.put("account_number", "system");
            insertMap.put("fieldName", "a1");
            insertMap.put("fileName", fileName);//全名
            insertMap.put("date", new Date());
            insertMap.put("coordinate", CoordinateMap.get("a1"));
            //插入第一页数据
            templateBeanMapper.insertByTemplateNameANDPageNo(insertMap);
            insertMap.put("fieldName", "a1");
            insertMap.put("coordinate", CoordinateMap.get("a198"));
            insertMap.put("pageNo",2);
            //插入第二页数据
            templateBeanMapper.insertByTemplateNameANDPageNo(insertMap);
            List<String> jpgList = MultiPageSplit.tiffToJPEG(tifURL, jpgURL);
            resultMap = TemplateRecognitionUtil.getTemplateResultMap(jpgList.get(0));
            resultMap.put("templateName", "t"+i);
            //插入识别标志
            templateRecognitionMapper.insertTemplate(resultMap);

            for(int j = 1; j<22; j++){
                Map<String, Object> updateMap1 = new HashMap<>();
                updateMap1.put("templateName", "t"+i);
                updateMap1.put("pageNo", 1);
                updateMap1.put("coordinate", CoordinateMap.get("a"+j));
                updateMap1.put("fieldName", "a"+j);
                templateBeanMapper.updateCoordinate(updateMap1);
            }


            for(Map.Entry<String, Object> m : resultMap.entrySet()){
                if(!m.getValue().equals("")){
                    if(m.getValue().equals("fHαm")) {
                        int R = Integer.parseInt(m.getKey().substring(1));//取第几行
                        int[] arr = CovertUtil.convert(coordinate[R-1]);//这一行"fHαm"坐标，用来确定y1,y2
                        String[] strings = {"a26", "a27", "a28", "a22", "a23", "a24","a25", "a29", "a30", "a31"};
                        //插入
                        for(String k : strings){
                            templateBeanMapper.updateCoordinate( CovertUtil.insert(CoordinateMap, k, arr, i));
                        }
                        continue;
                    }
                    else if(m.getValue().equals("fHα")) {
                        int R = Integer.parseInt(m.getKey().substring(1));
                        int[] arr = CovertUtil.convert(coordinate[R-1]);//这一行"fHαm"坐标，用来确定y1,y2
                        String[] strings = {"a50", "a51",
                                            "a68", "a69",
                                            "a86", "a87",
                                            "a32", "a33", "a34", "a35",
                                            "a95", "a96",
                                            "a77", "a78",
                                            "a59", "a60"};
                        //插入
                        for(String k : strings){
                            templateBeanMapper.updateCoordinate( CovertUtil.insert(CoordinateMap, k, arr, i));
                        }
                        continue;
                    }
                    else if(m.getValue().equals("Fα")) {
                        int R = Integer.parseInt(m.getKey().substring(1));
                        int[] arr = CovertUtil.convert(coordinate[R-1]);//这一行"fHαm"坐标，用来确定y1,y2
                        String[] strings = {"a52", "a53",
                                            "a70", "a71",
                                            "a88", "a89",
                                            "a36", "a37", "a38", "a39",
                                            "a97", "a98",
                                            "a79", "a80",
                                            "a61", "a62"};
                        //插入
                        for(String k : strings){
                            templateBeanMapper.updateCoordinate( CovertUtil.insert(CoordinateMap, k, arr, i));
                        }
                        continue;
                    }
                    else if(m.getValue().equals("ffα")) {
                        int R = Integer.parseInt(m.getKey().substring(1));
                        int[] arr = CovertUtil.convert(coordinate[R-1]);//这一行"fHαm"坐标，用来确定y1,y2
                        String[] strings = {"a54", "a55",
                                            "a72", "a73",
                                            "a90", "a91",
                                            "a40", "a41", "a42", "a43",
                                            "a99", "a100",
                                            "a81", "a82",
                                            "a63", "a64"};
                        //插入
                        for(String k : strings){
                            templateBeanMapper.updateCoordinate( CovertUtil.insert(CoordinateMap, k, arr, i));
                        }
                        continue;
                    }
                    else if(m.getValue().equals("Cα")) {
                        int R = Integer.parseInt(m.getKey().substring(1));
                        int[] arr = CovertUtil.convert(coordinate[R-1]);//这一行"fHαm"坐标，用来确定y1,y2
                        String[] strings = {"a56", "a74", "a92", "a44", "a45", "a101", "a83", "a65"};
                        //插入
                        for(String k : strings){
                            templateBeanMapper.updateCoordinate( CovertUtil.insert(CoordinateMap, k, arr, i));
                        }
                        continue;
                    }
                    else if(m.getValue().equals("fFu")) {
                        int R = Integer.parseInt(m.getKey().substring(1));
                        int[] arr = CovertUtil.convert(coordinate[R-1]);//这一行"fHαm"坐标，用来确定y1,y2
                        String[] strings = {"a57", "a75", "a93", "a46", "a47", "a102", "a84", "a66"};
                        //插入
                        for(String k : strings){
                            templateBeanMapper.updateCoordinate( CovertUtil.insert(CoordinateMap, k, arr, i));
                        }
                        continue;
                    }
                    else if(m.getValue().equals("fKO")) {
                        int R = Integer.parseInt(m.getKey().substring(1));
                        int[] arr = CovertUtil.convert(coordinate[R-1]);//这一行"fHαm"坐标，用来确定y1,y2
                        String[] strings = {"a58", "a76", "a94", "a48", "a49", "a103", "a85", "a67"};
                        //插入
                        for(String k : strings){
                            templateBeanMapper.updateCoordinate( CovertUtil.insert(CoordinateMap, k, arr, i));
                        }
                        continue;
                    }
                    else if(m.getValue().equals("fHβm")) {
                        int R = Integer.parseInt(m.getKey().substring(1));
                        int[] arr = CovertUtil.convert(coordinate[R-1]);//这一行"fHαm"坐标，用来确定y1,y2
                        String[] strings = {"a108", "a109", "a110", "a104", "a105", "a106", "a107", "a111", "a112", "a113"};
                        //插入
                        for(String k : strings){
                            templateBeanMapper.updateCoordinate( CovertUtil.insert(CoordinateMap, k, arr, i));
                        }
                        continue;
                    }else if(m.getValue().equals("fHβ")){
                        int R = Integer.parseInt(m.getKey().substring(1));
                        int[] arr = CovertUtil.convert(coordinate[R-1]);//这一行"fHαm"坐标，用来确定y1,y2
                        String[] strings = {"a132", "a133",
                                            "a150", "a151",
                                            "a168", "a169",
                                            "a114", "a115", "a116", "a117",
                                            "a177", "a178",
                                            "a159", "a160",
                                            "a141", "a142"};
                        //插入
                        for(String k : strings){
                            templateBeanMapper.updateCoordinate( CovertUtil.insert(CoordinateMap, k, arr, i));
                        }
                        continue;
                    }
                    else if(m.getValue().equals("Fβ")) {
                        int R = Integer.parseInt(m.getKey().substring(1));
                        int[] arr = CovertUtil.convert(coordinate[R-1]);//这一行"fHαm"坐标，用来确定y1,y2
                        String[] strings = {"a134", "a135",
                                            "a152", "a153",
                                            "a170", "a171",
                                            "a118", "a119", "a120", "a121",
                                            "a179", "a180",
                                            "a161", "a162",
                                            "a143", "a144"};
                        //插入
                        for(String k : strings){
                            templateBeanMapper.updateCoordinate( CovertUtil.insert(CoordinateMap, k, arr, i));
                        }
                        continue;
                    }
                    else if(m.getValue().equals("ffβ")) {
                        int R = Integer.parseInt(m.getKey().substring(1));
                        int[] arr = CovertUtil.convert(coordinate[R-1]);//这一行"fHαm"坐标，用来确定y1,y2
                        String[] strings = {"a136", "a137",
                                            "a154", "a155",
                                            "a172", "a173",
                                            "a122", "a123", "a124", "a125",
                                            "a181", "a182",
                                            "a163", "a164",
                                            "a145", "a146"};
                        //插入
                        for(String k : strings){
                            templateBeanMapper.updateCoordinate(CovertUtil.insert(CoordinateMap, k, arr, i));
                        }
                        continue;
                    }
                    else if(m.getValue().equals("Cβ")) {
                        int R = Integer.parseInt(m.getKey().substring(1));
                        int[] arr = CovertUtil.convert(coordinate[R-1]);//这一行"fHαm"坐标，用来确定y1,y2
                        String[] strings = {"a138", "a156", "a174", "a126", "a127", "a183", "a165", "a147"};
                        //插入
                        for(String k : strings){
                            templateBeanMapper.updateCoordinate( CovertUtil.insert(CoordinateMap, k, arr, i));
                        }
                        continue;
                    }
                    else if(m.getValue().equals("fu")) {
                        int R = Integer.parseInt(m.getKey().substring(1));
                        int[] arr = CovertUtil.convert(coordinate[R-1]);//这一行"fHαm"坐标，用来确定y1,y2
                        String[] strings = {"a139", "a157", "a175", "a128", "a129", "a184", "a166", "a148"};
                        //插入
                        for(String k : strings){
                            templateBeanMapper.updateCoordinate( CovertUtil.insert(CoordinateMap, k, arr, i));
                        }
                        continue;
                    }
                    else if(m.getValue().equals("fo")) {
                        int R = Integer.parseInt(m.getKey().substring(1));
                        int[] arr = CovertUtil.convert(coordinate[R-1]);//这一行"fHαm"坐标，用来确定y1,y2
                        String[] strings = {"a140", "a158", "a176", "a130", "a131", "a185", "a167", "a149"};
                        //插入
                        for(String k : strings){
                            templateBeanMapper.updateCoordinate( CovertUtil.insert(CoordinateMap, k, arr, i));
                        }
                        continue;
                    }
                    else try {
                            if(m.getValue().equals("t"+i)) continue;
                            else throw new Exception("找不到模板");
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                }
            }
        }

    }
}