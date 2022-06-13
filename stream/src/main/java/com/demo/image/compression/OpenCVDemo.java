package com.demo.image.compression;

import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/12
 * Time: 9:43
 * Description: No Description
 */
public class OpenCVDemo {
    public static void main(String[] args) {
        //初始化
        OpenCV.loadShared();
        //读取图片
        Mat src = Imgcodecs.imread("C:\\Users\\Administrator\\Desktop\\1655108227734.jpg");
        // 压缩图片
        /*MatOfInt 的构造参数是一个可变参数
        第一个参数 IMWRITE_JPEG_QUALITY 表示对图片的质量进行改变
        第二个是质量因子，1-100，值越大表示质量越高*/
        MatOfInt dstImage = new MatOfInt(Imgcodecs.IMWRITE_JPEG_QUALITY, 10);
        Imgcodecs.imwrite("C:\\Users\\Administrator\\Desktop\\wx_code.jpg",src,dstImage);
    }
}
