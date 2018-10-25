package com.eqy.utils;


/**
 * @ClassName RuleUtil
 * @Description 规则工具类
 * Created by chengkang
 * 2018/6/12 上午8:30
 */
public class RuleUtil {


    /**
     * @Description 规则提醒
     * @param result
     * @param expectArr
     * @return true or false
     */
    public static boolean tipRule(String result, String[] expectArr){
        for(String s : expectArr){
            if(s.equals(result))    return true;
        }
        return false;
    }

    /**
     * @Description 强制转换 keyBefore ---> keyAfter
     * @param result
     * @return newResult
     */
    public static String convertByForce(String result, String keyBefore, String keyAfter){
        if(keyBefore.equals(keyAfter)) return result;
        if(result==null) return "";
        else if(result.contains(keyBefore)){
            if(keyBefore.equals(".")) return result.replace(keyBefore, keyAfter);//正则表达式"."表示所有
            return result.replaceAll(keyBefore, keyAfter);
        }
        return result;
    }

    public static void main(String[] args)
    {
        System.out.println(convertByForce("-STI1118", "-","O"));
    }
}
