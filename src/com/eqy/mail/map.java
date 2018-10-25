package com.eqy.mail;

import java.util.HashMap;
import java.util.Map;

public class map {
	public static void main(String[] args) {

	    Map<String,Object> map = new HashMap<String,Object>();

	    map.put("apple", "新鲜的苹果");     //向列表中添加数据

	    map.put("computer", "配置优良的计算机");   //向列表中添加数据

	    map.put("book", null);     //向列表中添加数据

	    System.out.println("Map集合大小为："+map.size());

	}
}
