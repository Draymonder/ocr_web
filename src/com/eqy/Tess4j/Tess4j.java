package com.eqy.Tess4j;

import com.eqy.Tess4j.Tesseract.ITesseract;
import com.eqy.Tess4j.Tesseract.Tesseract;
import com.eqy.Tess4j.Tesseract.TesseractException;
import java.awt.image.BufferedImage;
import java.io.File;

public class Tess4j {

    //用于识别剪切图
    public static String covert(String filepath, String traineddata) {
        try {
            File imageFile = new File(filepath);//图片位置
            ITesseract instance = new Tesseract();  // JNA Interface Mapping
            //instance.setDatapath("/usr/local/apache-tomcat-7.0.88/webapps/ocr/tessdata/");
            instance.setLanguage(traineddata);//选择字库文件
            return instance.doOCR(imageFile).replaceAll("\n","" );//开始识别


        } catch (TesseractException e) {
            e.printStackTrace();
            return null;
        }
    }
    //用于识别图片流
    public static String covert(BufferedImage bi,  String traineddata) {
        try {
            ITesseract instance = new Tesseract();  // JNA Interface Mapping
           // instance.setDatapath("/usr/local/apache-tomcat-7.0.88/webapps/ocr/tessdata/");
            instance.setLanguage(traineddata);//选择字库文件
            return instance.doOCR(bi).replaceAll("\n","" );//开始识别
        } catch (TesseractException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(Tess4j.covert("/Users/chengkang/Desktop/11.jpg", "num"));
    }

}
    