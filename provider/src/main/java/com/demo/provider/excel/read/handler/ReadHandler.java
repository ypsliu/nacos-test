package com.demo.provider.excel.read.handler;

import com.alibaba.excel.EasyExcel;
import com.demo.provider.excel.read.entity.Meter;
import com.demo.provider.excel.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author lz
 * @date 2021-10-18 13:54
 */
@Slf4j
public class ReadHandler {

    public void simpleRead(){
        String fileName = FileUtil.getPath() + "excel" + File.separator + "meter.xlsx";
        try {
            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:/Users/84428/Desktop/del.txt")));
            EasyExcel.read(fileName, Meter.class, new PageReadListener<Meter>(dataList -> {
                for (Meter meter : dataList) {
                    try {
                        br.write("DEL meter_" + meter.getMeterNo() + "_" + meter.getProtocolId() + "\r\n");
                        log.info("write success:"+meter);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            })).sheet().doRead();
            br.flush();
            br.close();
            //批量执行redis脚本
            //cat del.txt | ./bin/redis-cli -p 6379 -a Zhuojianyun123321 --pine
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }

    public static void main(String[] args) {
        ReadHandler readHandler = new ReadHandler();
        readHandler.simpleRead();
    }

}
