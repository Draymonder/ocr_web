package com.eqy.utils;


import com.eqy.constants.SystemConstants;
import com.sun.media.jai.codec.*;
import org.springframework.beans.factory.annotation.Value;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RenderedOp;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengkang
 * 2018/5/31 下午3:05
 */
public class MultiPageSplit {

    //根据tiff文件路径分页，返回分页路径
    public static List<String> tiffToJPEG(String srcPath, String TEMP_PATH) {
        List<String> pathList = new ArrayList<>();
        FileSeekableStream ss = null;
        try {
            ss = new FileSeekableStream(srcPath);//以流形式取出tif文件数据
        } catch(IOException e) {
            System.out.println("Exception in " + srcPath + " " + e.getMessage());
            e.printStackTrace();
        }
        String tiffName;
        tiffName = srcPath.substring(srcPath.lastIndexOf("/") + 1, srcPath.lastIndexOf("."));//名称
        TIFFDecodeParam param0 = null;
        TIFFEncodeParam param = new TIFFEncodeParam();
        JPEGEncodeParam param1 = new JPEGEncodeParam();
        param1.setQuality(1.0f);
        if(ss != null) {
            ImageDecoder dec = ImageCodec.createImageDecoder("tiff", ss, param0);//将tif文件流转为image图片
            int count = 0;
            try {
                count = dec.getNumPages();//tif文件页数
            } catch(IOException e) {
                System.out.println("Exception in dec.getNumPages() " + e.getMessage());
                e.printStackTrace();
            }
            param.setCompression(TIFFEncodeParam.COMPRESSION_GROUP4);
            param.setLittleEndian(false); // Intel
            System.out.println("This TIF has " + count + " image(s)");
            for(int i = 0; i < count; i++) {//存储为jpeg图片
                RenderedImage page = null;
                File f = new File(TEMP_PATH, tiffName + "_" + i + ".jpg");
                pathList.add(TEMP_PATH + tiffName + "_" + i + ".jpg");
                try {
                    page = dec.decodeAsRenderedImage(i);//取出第i张image图片
                    if(!f.exists()) {
                        f.getParentFile().mkdirs();
                        f.createNewFile();
                    }
                } catch(IOException e) {
                    System.out.println("Exception in File f or dec.decodeAsRenderedImage(i)" + e.getMessage());
                    e.printStackTrace();
                }
                if(f.exists() && page != null) {//压缩图片并保存为JPEG格式
                    ParameterBlock pb = new ParameterBlock();
                    pb.addSource(page);
                    pb.add(f.toString());
                    pb.add("JPEG");
                    pb.add(param1);
                    RenderedOp r = JAI.create("filestore", pb);
                    r.dispose();
                }
            }
        }
        return pathList;
    }
}
