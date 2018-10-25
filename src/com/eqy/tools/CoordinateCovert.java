package com.eqy.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengkang
 * 2018/6/20 上午11:42
 */
public class CoordinateCovert {
    /**
     * 将 "12,12,12,12"--> 12 12 12 12
     * @param s String
     * @return int[]
     */
    public static int[] transform(String s){
        String[] Array = s.split(",");
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
            int x = x1 < x2 ? x1 : x2;
            int y = y1 < y2 ? y1 : y2;
            int width = Math.abs(x2-x1);
            int height = Math.abs(y2-y1);
            return new int[]{x, y, width, height};
        }
        else{
            for (int i = 0; i<result.length; i++){
                result_int[i] = result[i];
            }
        }
        return  result_int;
    }

    public static void main(String[] args) {
       // System.out.println(transform( "23,123,123,12,23" )[2]);
    }
}
