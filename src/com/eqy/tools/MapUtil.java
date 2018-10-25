package com.eqy.tools;

import java.util.*;

/**
 * Created by chengkang
 * 2018/6/15 下午2:06
 */
public class MapUtil {
    /**
     *
     * @param mapA 待匹配图片的特殊字段键值对 r1:v1, r2:v2,...,r14:v14
     * @param mapB 模板库内对应字段键值对
     * @return
     */
    public static boolean compareMap(Map<String, Object> mapA, Map<String, Object> mapB){
        for(Iterator<Map.Entry<String, Object>> it = mapA.entrySet().iterator();it.hasNext();){
            String key = it.next().getKey();
            if(!mapB.get(key).toString().toUpperCase().equals(mapA.get(key).toString().toUpperCase())) return false;
        }
        return true;
    }

    /**
     * 使用 Map按key进行排序a1,a2
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<>(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        int first = Integer.parseInt (o1.replaceAll("^[a-zA-Z]", ""));
                        int second = Integer.parseInt (o2.replaceAll ("^[a-zA-Z]", ""));
                        if (first > second) {
                            return 1;
                        }
                        else if (first < second) {
                            return -1;
                        }
                        else {
                            return 0;
                        }
                    }

                    @Override
                    public boolean equals(Object obj) {
                        return false;
                    }
                });

        sortMap.putAll(map);
        return sortMap;
    }


    public static void main(String[] args) {
        Map<String, String> map = new TreeMap<String, String>();

        map.put("a12", "kfc");
        map.put("c23", "wnba");
        map.put("d13", "nba");
        map.put("b4", "cba");
        map.put("b1", null);

        System.out.println(sortMapByKey(map));
        //System.out.println(map);

       // Map<String, String> map1 = new TreeMap<>();
       // for(Iterator<Map.Entry<String, String>> it = map.entrySet().iterator(); it.hasNext();){
        //    String key = it.next().getKey();
        //    String val = map.get(key);
        //    map1.put(key,val);
        //}
        ///System.out.println(map1);
    }
}
