package com.eqy.web.service.traineddata;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.eqy.web.pojo.TNgcMail;


public interface ITraineddataService {
   
	void displayMailManage(HttpServletRequest request,Model model);
	

}
