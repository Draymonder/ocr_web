package com.eqy.utils;

import java.util.Date;

public class Halfyear {
    public static String  getHalfyear(){
    	String rq=DateTimeUtil.getChar(new Date()).substring(0,7);//当前月
    	String rq1=DateTimeUtil.getDateAdd(rq+"-01",-10);//当前月
    	String rq2=DateTimeUtil.getDateAdd(rq1+"-01",-10);
    	String rq3=DateTimeUtil.getDateAdd(rq2+"-01",-10);
    	String rq4=DateTimeUtil.getDateAdd(rq3+"-01",-10);
    	String rq5=DateTimeUtil.getDateAdd(rq4+"-01",-10);
    	String rq6=DateTimeUtil.getDateAdd(rq5+"-01",-10);
    	String rq7=DateTimeUtil.getDateAdd(rq6+"-01",-10);
    	String rq8=DateTimeUtil.getDateAdd(rq7+"-01",-10);
    	String rq9=DateTimeUtil.getDateAdd(rq8+"-01",-10);
    	String rq10=DateTimeUtil.getDateAdd(rq9+"-01",-10);
    	String rq11=DateTimeUtil.getDateAdd(rq10+"-01",-10);
    	
    	String result=rq11+","+rq10+","+rq9+","+rq8+","+rq7+","+rq6+","+rq5+","+rq4+","+rq3+","+rq2+","+rq1+","+rq;
    	return result;
    }
}
