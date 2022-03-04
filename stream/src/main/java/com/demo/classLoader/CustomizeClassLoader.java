package com.demo.classLoader;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/4
 * Time: 16:27
 * Description: 自定义类加载器
 * 要创建用户自己的类加载器，只需要继承java.lang.ClassLoader类，
 * 然后覆盖它的findClass(String name)方法即可，即指明如何获取类的字节码流。
 *
 * 如果要符合双亲委派规范，则重写findClass方法（用户自定义类加载逻辑）；
 * 要破坏的话，重写loadClass方法(双亲委派的具体逻辑实现)。
 */
public class CustomizeClassLoader extends ClassLoader{

    private String classPath;

    public CustomizeClassLoader(String classPath){
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        byte[] classData = getData(name);
        if(classData == null){
            throw new ClassNotFoundException();
        }else {
            return defineClass(name,classData,0,classData.length);
        }
    }

    private byte[] getData(String className){
        String path = classPath + File.separatorChar
                + className.replace('.',File.separatorChar) + ".class";
        try {
            InputStream inputStream = new FileInputStream(path);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int num = 0;
            while ((num = inputStream.read(buffer))!=-1){
                stream.write(buffer,0,num);
            }
            return stream.toByteArray();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
        return null;
    }


    class TestClassLoader{
        @Override
        public String toString(){
            return "自定义类加载成功";
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader classLoader = new CustomizeClassLoader("C:\\Users\\Administrator\\IdeaProjects\\nacos\\stream\\target\\classes");
        Class c = classLoader.loadClass("com.demo.classLoader.TestClassLoaderPath");
        System.out.println(c.newInstance());
    }
}
