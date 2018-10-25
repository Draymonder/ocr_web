package com.eqy.utils;



import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.media.jai.codec.JPEGEncodeParam;
import com.sun.media.jai.codecimpl.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by chengkang
 * 2018/7/26 下午3:30
 */
public class newOperateImg {

    /**
     * 对不同类型图片进行裁剪
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param srcPath
     * @param subPath
     * @throws IOException
     */
    public static String OperateImgCut(int x, int y, int width, int height, String srcPath, String subPath) throws IOException {
        File f = new File(srcPath);
        BufferedImage sub_bi = ImageIO.read(f).getSubimage(x, y, width, height);
        if(readType(f).equals("png")){
            cutPNG(sub_bi, subPath);
            return ".png";
        }
        else if(readType(f).equals("jpeg")){
            cutJPG(sub_bi, subPath);
            return ".jpg";
        }
        else if(readType(f).equals("tif")){
            cutTIF(sub_bi, subPath);
            return ".tif";
        }
        return "";
    }

    public static BufferedImage GetBufferedImg(int x, int y, int width, int height, String srcPath) throws IOException {
        File f = new File(srcPath);
        BufferedImage sub_bi = ImageIO.read(f).getSubimage(x, y, width, height);
        return sub_bi;
    }


    private static void cutJPG(BufferedImage sub_bi, String subPath) throws IOException {
        String subPath1 = subPath+".jpg";
        ImageIO.write(sub_bi, "jpeg", new File(subPath1));
    }

    private static void cutPNG(BufferedImage sub_bi, String subPath) throws IOException {
        String subPath1 = subPath+".png";
        ImageIO.write(sub_bi, "png", new File(subPath1));
    }

    private static void cutTIF(BufferedImage sub_bi, String subPath) throws IOException {
        String subPath1 = subPath+".tif";
        ImageIO.write(sub_bi, "tiff", new File(subPath1));
    }

//    public static String getFormatName(Object o) {
//        try {
//            ImageInputStream iis = ImageIO.createImageInputStream(o);
//            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
//            if(!iter.hasNext()) {
//                return null;
//            }
//            ImageReader reader = iter.next();
//            iis.close();
//            return reader.getFormatName();
//        } catch(IOException e) {
//        }
//        return null;
//    }

    private static boolean isPNG(byte[] buf) {
        byte[] markBuf = {(byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A}; //PNG识别符
        return compare(buf, markBuf);
    }

    private static boolean isJPEGHeader(byte[] buf) {
        byte[] markBuf = {(byte) 0xff, (byte) 0xd8}; //JPEG开始符

        return compare(buf, markBuf);
    }

    private static boolean isJPEGFooter(byte[] buf) {//JPEG结束符
        byte[] markBuf = {(byte) 0xff, (byte) 0xd9};
        return compare(buf, markBuf);
    }
    private static boolean isTIF(byte[] buf) {//TIF结束符
        byte[] markBuf = {(byte) 0x49, (byte) 0x49};
        byte[] markBuf1 = {(byte) 0x4D, (byte) 0x4D};
        return compare(buf, markBuf)||compare(buf, markBuf1);
    }

    /**
     * 读取文件类型
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static String readType(String filename) throws IOException {
        FileInputStream fis = null;
        try {
            File f = new File(filename);
            if(!f.exists() || f.isDirectory() || f.length() < 8) {
                throw new IOException("the file [" + f.getAbsolutePath() + "] is not image !");
            }

            fis = new FileInputStream(f);
            byte[] bufHeaders = readInputStreamAt(fis, 0, 8);
            if(isJPEGHeader(bufHeaders)) {
                long skiplength = f.length() - 2 - 8; //第一次读取时已经读了8个byte,因此需要减掉
                byte[] bufFooters = readInputStreamAt(fis, skiplength, 2);
                if(isJPEGFooter(bufFooters)) {
                    return "jpeg";
                }
            } else if(isPNG(bufHeaders)) {
                return "png";
            } else if(isTIF(bufHeaders)) {
                return "tif";
            }
            else return "";

        } catch(FileNotFoundException e) {
            throw e;
        } finally {
            try {
                if(fis != null) fis.close();
            } catch(Exception e) {
            }
        }
        return "";
    }
    public static String readType(File f) throws IOException {
        FileInputStream fis = null;
        try {
            if(!f.exists() || f.isDirectory() || f.length() < 8) {
                throw new IOException("the file [" + f.getAbsolutePath() + "] is not image !");
            }
            fis = new FileInputStream(f);
            byte[] bufHeaders = readInputStreamAt(fis, 0, 8);
            if(isJPEGHeader(bufHeaders)) {
                long skiplength = f.length() - 2 - 8; //第一次读取时已经读了8个byte,因此需要减掉
                byte[] bufFooters = readInputStreamAt(fis, skiplength, 2);
                if(isJPEGFooter(bufFooters)) {
                    return "jpeg";
                }
            } else if(isPNG(bufHeaders)) {
                return "png";
            } else if(isTIF(bufHeaders)) {
                return "tif";
            }
            else return "";

        } catch(FileNotFoundException e) {
            throw e;
        } finally {
            try {
                if(fis != null) fis.close();
            } catch(Exception e) {
            }
        }
        return "";
    }

    /**
     * 标示一致性比较
     *
     * @param buf     待检测标示
     * @param markBuf 标识符字节数组
     * @return 返回false标示标示不匹配
     */
    private static boolean compare(byte[] buf, byte[] markBuf) {
        for(int i = 0; i < markBuf.length; i++) {
            byte b = markBuf[i];
            byte a = buf[i];

            if(a != b) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param fis        输入流对象
     * @param skiplength 跳过位置长度
     * @param length     要读取的长度
     * @return 字节数组
     * @throws IOException
     */
    private static byte[] readInputStreamAt(FileInputStream fis, long skiplength, int length) throws IOException {
        byte[] buf = new byte[length];
        fis.skip(skiplength);  //
        int read = fis.read(buf, 0, length);
        return buf;
    }

    public static void main(String[] args) throws IOException {
        File f = new File("/Users/chengkang/Desktop/1.jpg");

        String subPath1 = "/Users/chengkang/Desktop/2.jpg";
        BufferedImage sub_bi = ImageIO.read(f);
        ImageIO.write(sub_bi,"jpeg",new File("/Users/chengkang/Desktop/3.jpg"));



    }
}
