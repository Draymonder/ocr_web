package com.eqy.utils;

import com.eqy.Tess4j.Tess4j;
import com.eqy.constants.SystemConstants;
import com.eqy.tools.CoordinateCovert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengkang
 * 2018/7/2 上午9:41
 */
public class TemplateRecognitionUtil {
        //jpg路径
        public static Map<String, Object> getTemplateResultMap(String jpgPath){

            int[] Arr;
            String result;
            Map<String, Object> ResultMap = new HashMap<>();//识别结果存Map
            for(int i = 0; i < SystemConstants.TEMPLATE_RECOGNITON_COORDINATE.length; i++) {
                Arr = CoordinateCovert.transform(SystemConstants.TEMPLATE_RECOGNITON_COORDINATE[i]);
                try {
                    result = Tess4j.covert(new OperateImg(Arr[0], Arr[1], Arr[2], Arr[3], jpgPath).getBufferedImage(), "num");
                    result = result.replaceAll("°|0", "o");
                    if(!SystemConstants.TEMPLATE_RECOGNITON_FIELD.contains(result.toUpperCase())) result = ""; //处理特殊情况 R/T [mm]
                    ResultMap.put("r"+(i+1), result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return ResultMap;
        }
    }


