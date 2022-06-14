package com.demo.image.compression;

import nu.pattern.OpenCV;
import org.junit.Test;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/12
 * Time: 9:43
 * Description: No Description
 */
public class OpenCVDemo {

    @Test
    public  void compression() {
        //初始化
        OpenCV.loadShared();
        //读取图片
        Mat src = Imgcodecs.imread("C:\\Users\\Administrator\\Desktop\\xln\\icon.jpg");
        // 压缩图片
        /*MatOfInt 的构造参数是一个可变参数
        第一个参数 IMWRITE_JPEG_QUALITY 表示对图片的质量进行改变
        第二个是质量因子，1-100，值越大表示质量越高*/
        MatOfInt dstImage = new MatOfInt(Imgcodecs.IMWRITE_JPEG_QUALITY, 10);
        Imgcodecs.imwrite("C:\\Users\\Administrator\\Desktop\\icon.jpg",src,dstImage);
    }

    @Test
    public void resize(){
        //初始化
        OpenCV.loadShared();
        //读取图片
        Mat src = Imgcodecs.imread("C:\\Users\\Administrator\\Desktop\\xln\\icon.jpg");

        Mat dst = new Mat();

        Imgproc.resize(src,dst,new Size(32.00,32.00) ,0, 0, Imgproc.INTER_AREA);

        // Imgproc.INTER_LINEAR 放大

        Imgcodecs.imwrite("C:\\Users\\Administrator\\Desktop\\icon.jpg",dst);
    }
}
