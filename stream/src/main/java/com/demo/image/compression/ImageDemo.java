package com.demo.image.compression;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/12
 * Time: 9:52
 * Description: No Description
 */
public class ImageDemo {
    public static void main(String[] args) throws IOException {
        File input = new File("C:\\Users\\Administrator\\Desktop\\resized_image.jpg");
        BufferedImage image = ImageIO.read(input);
        Iterator<ImageWriter> writerIterator = ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = (ImageWriter) writerIterator.next();
        File compressedImageFile = new File("C:\\Users\\Administrator\\Desktop\\resized_image_twice.jpg");
        OutputStream outputStream = new FileOutputStream(compressedImageFile);
        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
        writer.setOutput(imageOutputStream);

        ImageWriteParam imageWriteParam = writer.getDefaultWriteParam();
        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        imageWriteParam.setCompressionQuality(0.01f);

        writer.write(null,new IIOImage(image,null,null),imageWriteParam);

        outputStream.close();
        imageOutputStream.close();
        writer.dispose();
    }
}
