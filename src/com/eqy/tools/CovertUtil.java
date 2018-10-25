package com.eqy.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengkang
 * 2018/7/30 上午11:49
 */
public class CovertUtil {
    public static int[] convert(String strings){
        String[] Array = strings.split(",");
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(String ss: Array){
            arrayList.add( Integer.parseInt(ss));
        }
        Integer[] result = new Integer[arrayList.size()];
        result = arrayList.toArray(result);
        int[] result_int = new int[result.length];
        if(result_int.length == 4){
            int x1 = result[0];
            int y1 = result[1];
            int x2 = result[2];
            int y2 = result[3];
            return new int[]{x1, y1, x2, y2};
        }
        else{
            for (int i = 0; i<result.length; i++){
                result_int[i] = result[i];
            }
        }
        return  result_int;
    }
    public static Map<String, Object> insert(Map<String, Object> CoordinateMap, String k, int[] arr, int i){
        int[] output = CovertUtil.convert(CoordinateMap.get(k).toString());//找出full模板里当前字段坐标，用来确定x1,x2
        int x1 = output[0];
        int x2 = output[2];
        int y1 = arr[1];
        int y2 = arr[3];
        String input = x1+","+y1+","+x2+","+y2;
        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("templateName", "t"+i);
        updateMap.put("pageNo", 1);
        updateMap.put("coordinate", input);
        updateMap.put("fieldName", k);
        return updateMap;
    }
    public static void main(String[] args) {
        int[] t = convert("22,22,34,23");
        for(int e :t){
            System.out.println(e);
        }
    }
}
