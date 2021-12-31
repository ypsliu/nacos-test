package com.demo.effective;

import java.io.*;

/**
 * @author lz
 * @date 2021-12-06 13:20
 */
public class TryWithResources {
    static String firstLineOfFile(String path) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(
                new FileReader(path)
        )){
            return bufferedReader.readLine();
        }
    }


    static String firstLineOfFile(String path,String defaultValue) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(
                new FileReader(path)
        )){
            return bufferedReader.readLine();
        }catch (IOException e){
            return defaultValue;
        }
    }


    static void copy(String src,String dst,int bufferSize) throws IOException{
        try (InputStream inputStream = new FileInputStream(src);
            OutputStream outputStream = new FileOutputStream(dst)
        ){
            byte[] buf = new byte[bufferSize];
            int n;
            while ((n = inputStream.read(buf)) >= 0){
                outputStream.write(buf,0,n);
            }
        }
    }
}
