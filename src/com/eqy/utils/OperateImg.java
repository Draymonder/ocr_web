package com.eqy.utils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * @ClassName: OperateImg
 * @Description:
 * @author: chengkang
 * @date: 2018/5/23 下午4:09
 */


public class OperateImg {

    //上传到服务器后的图片路径
    private String srcPath;

    //所截取图片的保存路径
    private String subPath;

    //截取矩形框左上角横坐标
    private int x ;

    //截取矩形框左上角纵坐标
    private int y ;

    //截取矩形框宽度
    private  int width ;

    //截取矩形框高度
    private  int height ;

    public OperateImg() {}

    public OperateImg(int x, int y, int width, int height, String srcPath, String subPath) {
        this .x = x;
        this .y = y;
        this .width = width;
        this .height = height;
        this.srcPath = srcPath;
        this.subPath = subPath;
    }
    public OperateImg(int x, int y, int width, int height, String srcPath) {
        this .x = x;
        this .y = y;
        this .width = width;
        this .height = height;
        this.srcPath = srcPath;
    }

    /**
     * 方法描述: 截取源图片并将截取到的图片保存
     */
    public void cut()throws IOException {

        FileInputStream is =  null ;
        ImageInputStream iis = null ;

        try {
            // 读取图片文件
            is = new FileInputStream(srcPath);
            /*
             * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader
             * 声称能够解码指定格式。 参数：formatName - 包含非正式格式名称 .
             *（例如 "jpeg" 或 "tiff"）等 。
             */
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpeg");
            ImageReader reader = it.next();
            // 获取图片流
            iis = ImageIO.createImageInputStream(is);
            /*
             * <p>iis:读取源.true:只向前搜索 </p>.将它标记为 ‘只向前搜索'。
             * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader
             * 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
             */
            reader.setInput(iis, true ) ;

            /*
             * <p>描述如何对流进行解码的类<p>.用于指定如何在输入时从 Java Image I/O
             * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件
             * 将从其 ImageReader 实现的 getDefaultReadParam 方法中返回
             * ImageReadParam 的实例。
             */
            ImageReadParam param = reader.getDefaultReadParam();
            /*
             * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
             * 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。
             */
            Rectangle rect =  new Rectangle(x, y, width, height);

            // 提供一个 BufferedImage，将其用作解码像素数据的目标。
            param.setSourceRegion(rect);

            /*
             * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将
             * 它作为一个完整的 BufferedImage 返回。
             */
            BufferedImage bi = reader.read(0,param);

            // 保存新图片
            ImageIO.write(bi,"jpeg",new File(subPath));

        } finally {
            if (is != null )
                is.close() ;
            if (iis != null )
                iis.close();
          }
    }
    public void cutJPG() throws IOException {
        BufferedImage bi = ImageIO.read(new File(srcPath));
        ImageIO.write(bi.getSubimage(x,y, width, height), "jpeg",new File(subPath));
    }
    public BufferedImage getBufferedImage()throws IOException {

        FileInputStream is =  null ;
        ImageInputStream iis = null ;

        try {
            // 读取图片文件
            is = new FileInputStream(srcPath);
            /*
             * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader
             * 声称能够解码指定格式。 参数：formatName - 包含非正式格式名称 .
             *（例如 "jpeg" 或 "tiff"）等 。
             */
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpeg");
            ImageReader reader = it.next();
            // 获取图片流
            iis = ImageIO.createImageInputStream(is);
            /*
             * <p>iis:读取源.true:只向前搜索 </p>.将它标记为 ‘只向前搜索'。
             * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader
             * 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
             */
            reader.setInput(iis, true ) ;

            /*
             * <p>描述如何对流进行解码的类<p>.用于指定如何在输入时从 Java Image I/O
             * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件
             * 将从其 ImageReader 实现的 getDefaultReadParam 方法中返回
             * ImageReadParam 的实例。
             */
            ImageReadParam param = reader.getDefaultReadParam();

            /*
             * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
             * 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。
             */
            Rectangle rect =  new Rectangle(x, y, width, height);

            // 提供一个 BufferedImage，将其用作解码像素数据的目标。
            param.setSourceRegion(rect);

            /*
             * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将
             * 它作为一个完整的 BufferedImage 返回。
             */
            BufferedImage bi = reader.read(0,param);
            return bi;
        } finally {
            if (is != null )
                is.close() ;
            if (iis != null )
                iis.close();
        }
    }

    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    public String getSubPath() {
        return subPath;
    }

    public void setSubPath(String subPath) {
        this.subPath = subPath;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static void main(String[] args) throws IOException {
        long s1 = System.currentTimeMillis();
        new OperateImg(415,250,1038,292,"/Users/chengkang/Desktop/test.jpg","/Users/chengkang/Desktop/cut1.jpg").cut();
        long e1 = System.currentTimeMillis();
        System.out.println(e1-s1);
        long s2 = System.currentTimeMillis();
        new OperateImg(415,250,1038,292,"/Users/chengkang/Desktop/test.jp g","/Users/chengkang/Desktop/cut2.jpg").cutJPG();
        long e2 =System.currentTimeMillis();
        System.out.println(e2-s2);

    }


}
